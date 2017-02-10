/*
  Copyright (c) 2016 Matthias Eichner
  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
  documentation files (the "Software"), to deal in the Software without restriction, including without
  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
  conditions:

  The above copyright notice and this permission notice shall be included in all copies or substantial
  portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
  TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
  THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
  CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
  DEALINGS IN THE SOFTWARE.
*/
package org.mycore.xml;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mycore.xml.abbyy.v10.BlockType;
import org.mycore.xml.abbyy.v10.CharParamsType;
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.abbyy.v10.FormattingType;
import org.mycore.xml.abbyy.v10.LineType;
import org.mycore.xml.abbyy.v10.ParagraphType;
import org.mycore.xml.alto.v2.Alto;
import org.mycore.xml.alto.v2.Alto.Description;
import org.mycore.xml.alto.v2.Alto.Layout;
import org.mycore.xml.alto.v2.Alto.Layout.Page;
import org.mycore.xml.alto.v2.Alto.Styles;
import org.mycore.xml.alto.v2.Alto.Styles.TextStyle;
import org.mycore.xml.alto.v2.ComposedBlockType;
import org.mycore.xml.alto.v2.GraphicalElementType;
import org.mycore.xml.alto.v2.IllustrationType;
import org.mycore.xml.alto.v2.PageSpaceType;
import org.mycore.xml.alto.v2.StringType;
import org.mycore.xml.alto.v2.TextBlockType;
import org.mycore.xml.alto.v2.TextBlockType.TextLine;
import org.mycore.xml.alto.v2.TextBlockType.TextLine.SP;

/**
 * Base class to convert a single abbyy xml file to an alto one. Just call {@link #convert(Document)}.
 * You can use {@link JAXBUtil#unmarshalAbbyyDocument(java.io.InputStream)} to load an abbyy document.
 * 
 * @author Matthias Eichner
 */
public class AbbyyToAltoConverter {

    private static Logger LOGGER = LogManager.getLogger(AbbyyToAltoConverter.class);

    /**
     * Converts the given abbyy document to an ALTO one.
     * 
     * @param abbyyXML the abbyy document
     * @return ALTO xml
     */
    public Alto convert(Document abbyyDocument) {
        Alto alto = buildAlto();
        Page page = buildAltoPage(alto);

        abbyyDocument.getPage().stream().findFirst().ifPresent(abbyyPage -> {
            page.setWIDTH(abbyyPage.getWidth().intValue());
            page.setHEIGHT(abbyyPage.getHeight().intValue());
        });
        PageSpaceType pageSpace = new PageSpaceType();
        page.setPrintSpace(pageSpace);

        Stream<BlockType> blockStream = abbyyDocument.getPage().stream().flatMap(p -> p.getBlock().stream());

        Rectangle pageRect = new Rectangle();

        AtomicInteger composedBlockCount = new AtomicInteger(0);
        AtomicInteger paragraphCount = new AtomicInteger(0);
        AtomicInteger illustrationCount = new AtomicInteger(0);
        AtomicInteger graphicalElementCount = new AtomicInteger(0);
        AtomicInteger tableElementCount  = new AtomicInteger(0);

        blockStream.forEach(abbyyBlock -> {
            String blockType = abbyyBlock.getBlockType();

            Rectangle blockRect = new Rectangle(abbyyBlock);
            pageRect.maximize(blockRect);

            if (blockType.equals("Text")) {
                ComposedBlockType composedBlock = new ComposedBlockType();
                abbyyBlock.getText().stream().flatMap(text -> text.getPar().stream()).forEach(abbyyParagraph -> {
                    handleParagraph(alto, composedBlock, abbyyParagraph, paragraphCount);
                });
                if (composedBlock.getContent().isEmpty()) {
                    return;
                }
                composedBlock.setTYPE("text");
                blockRect.applyOnBlock(composedBlock);
                composedBlock.setID("ComposedBlock_" + composedBlockCount.incrementAndGet());
                pageSpace.getContent().add(composedBlock);
            } else if (blockType.equals("Picture")) {
                IllustrationType illustration = new IllustrationType();
                blockRect.applyOnBlock(illustration);
                illustration.setID("Illustration_" + illustrationCount.incrementAndGet());
                pageSpace.getContent().add(illustration);
            } else if (blockType.equals("Table")) {
                ComposedBlockType tableBlock = new ComposedBlockType();
                tableBlock.setTYPE("table");
                blockRect.applyOnBlock(tableBlock);
                abbyyBlock.getRow().stream().flatMap(row -> row.getCell().stream())
                    .flatMap(cell -> cell.getText().stream()).flatMap(text -> text.getPar().stream())
                    .forEach(abbyyParagraph -> {
                        handleParagraph(alto, tableBlock, abbyyParagraph, paragraphCount);
                    });
                tableBlock.setID("Table_" + tableElementCount.incrementAndGet());
                pageSpace.getContent().add(tableBlock);
            } else if (blockType.equals("Separator") || blockType.equals("SeparatorsBox")) {
                GraphicalElementType graphicalSeparator = new GraphicalElementType();
                blockRect.applyOnGraphicalElement(graphicalSeparator);
                graphicalSeparator.setID("GraphicalElement_" + graphicalElementCount.incrementAndGet());
                pageSpace.getContent().add(graphicalSeparator);
            } else {
                throw new ConvertException("Invalid block type " + blockType + " at " + blockRect);
            }
        });

        pageRect.applyOnPageSpace(pageSpace);
        optimizeFonts(pageSpace);
        return alto;
    }

    /**
     * Builds an empty {@link Alto} object with description and styles.
     * 
     * @return alto as java object
     */
    private Alto buildAlto() {
        Alto alto = new Alto();
        alto.setDescription(buildDescription(alto));
        alto.setStyles(new Styles());
        return alto;
    }

    private Description buildDescription(Alto alto) {
        Description description = new Description();
        description.setMeasurementUnit("pixel");
        return description;
    }

    private Page buildAltoPage(Alto alto) {
        Layout layout = new Layout();
        alto.setLayout(layout);
        Page page = new Page();
        layout.getPage().add(page);
        page.setID("Page");
        return page;
    }

    /**
     * Handles the convert process of one abbyy paragraph.
     * 
     * @param alto the alto document
     * @param composedBlock the parent alto composed block
     * @param abbyyParagraph the source abbyy paragraph
     * @param paragraphCount an count of how many paragraphs are already added, used for id generation of
     *        the alto paragraph's (text block's)
     */
    private void handleParagraph(Alto alto, ComposedBlockType composedBlock, ParagraphType abbyyParagraph,
        AtomicInteger paragraphCount) {
        TextBlockType paragraphBlock = new TextBlockType();
        Rectangle textBlockRect = new Rectangle();
        abbyyParagraph.getLine().forEach(abbyyLine -> {
            handleLine(alto, paragraphBlock, textBlockRect, abbyyLine);
        });
        if (abbyyParagraph.getLine().isEmpty()) {
            return;
        }
        textBlockRect.applyOnBlock(paragraphBlock);
        paragraphBlock.setID("Paragraph_" + paragraphCount.incrementAndGet());
        composedBlock.getContent().add(paragraphBlock);
    }

    /**
     * Handles the convert process of one abbyy text line.
     * 
     * @param alto the alto document
     * @param textBlock the alto text blocl
     * @param textBlockRect an rectangle with the bounds of the alto block
     * @param abbyyLine the source abbyy text line
     */
    private void handleLine(Alto alto, TextBlockType textBlock, Rectangle textBlockRect, LineType abbyyLine) {
        Rectangle lineRect = new Rectangle(abbyyLine);
        if (lineRect.area() == 0) {
            return;
        }
        textBlockRect.maximize(lineRect);
        TextLine altoLine = new TextLine();
        lineRect.applyOnLine(altoLine);
        textBlock.getTextLine().add(altoLine);

        abbyyLine.getFormatting().forEach(abbyyFormatting -> {
            handleFormatting(alto, altoLine, abbyyFormatting);
        });
    }

    /**
     * Handles the convert process of one abbyy formatting.
     * 
     * @param alto the alto document
     * @param altoLine the alto line where the abbyy formatting is added to
     * @param abbyyFormatting the source abbyy formatting
     */
    private void handleFormatting(Alto alto, TextLine altoLine, FormattingType abbyyFormatting) {
        // get the text style for this formatting
        TextStyle style = getTextStyle(alto.getStyles(), abbyyFormatting);

        // get the characters of the formatting
        Stream<CharParamsType> charParamsStream = abbyyFormatting.getContent().stream().filter(c -> {
            return c instanceof JAXBElement && ((JAXBElement<?>) c).getValue() instanceof CharParamsType;
        }).map(c -> (CharParamsType) ((JAXBElement<?>) c).getValue());

        // build the word list
        List<Word> words = new ArrayList<>();
        AtomicReference<Word> wordRef = new AtomicReference<>();
        charParamsStream.forEach(charParam -> {
            String value = charParam.getContent().stream().filter(c -> c instanceof String).map(String.class::cast)
                .collect(Collectors.joining()).trim();
            if (value.isEmpty()) {
                Word space = new Word(true);
                space.addCharParam(charParam);
                words.add(space);
                wordRef.set(null);
                return;
            }
            if (wordRef.get() == null) {
                wordRef.set(new Word(false));
                words.add(wordRef.get());
            }
            wordRef.get().addCharParam(charParam);
        });

        // add the words to the alto line
        addToLine(altoLine, style, words);
    }

    /**
     * Adds each word of the word list to the line with the given text style.
     * 
     * @param altoLine the alto line where the words are added to
     * @param style the text style of the line
     * @param words a list of words
     */
    private void addToLine(TextLine altoLine, TextStyle style, List<Word> words) {
        words.forEach(word -> {
            Rectangle wordRect = word.getRectangle();
            if (word.isSpace()) {
                SP sp = new SP();
                wordRect.applyOnSP(sp);
                altoLine.getStringAndSP().add(sp);
            } else {
                StringType string = new StringType();
                string.setCONTENT(word.getValue());
                try {
                    string.setWC(word.getWC());
                } catch (Exception exc) {
                    LOGGER.warn("Error while getting word confidence (WC) of " + word.getValue());
                    string.setWC(0f);
                }
                try {
                    string.setCC(word.getCC());
                } catch(Exception exc) {
                    LOGGER.warn("Error while getting character confidence (CC) of " + word.getValue());
                }
                string.getSTYLEREFS().add(style);
                wordRect.applyOnString(string);
                altoLine.getStringAndSP().add(string);
            }
        });
    }

    /**
     * Gets the alto {@link #TextStyle} by the abbyy formatting.
     * 
     * @param styles the alto styles
     * @param abbyyFormatting the abbyy formatting
     * @return
     */
    private TextStyle getTextStyle(Styles styles, FormattingType abbyyFormatting) {
        String fontFamily = abbyyFormatting.getFf();
        Float fontSize = abbyyFormatting.getFs();
        List<String> fontStyles = new ArrayList<>();
        if (abbyyFormatting.isBold()) {
            fontStyles.add("bold");
        }
        if (abbyyFormatting.isItalic()) {
            fontStyles.add("italic");
        }
        if (abbyyFormatting.isUnderline()) {
            fontStyles.add("underline");
        }
        return getTextStyle(styles, fontFamily, fontSize, fontStyles);
    }

    /**
     * Gets the {@link #TextStyle} for the given font family and size. Each alto document
     * have a pre defined list of fonts. If no text style is found, this method creates
     * an appropriate one.
     * 
     * @param styles the alto styles
     * @param fontFamily the font family
     * @param fontSize the font size
     * @param fontStyles list of font styles e.g. bold or italics
     * @return the text style
     */
    private TextStyle getTextStyle(Styles styles, String fontFamily, Float fontSize, List<String> fontStyles) {
        List<TextStyle> textStyles = styles.getTextStyle();
        TextStyle textStyle = textStyles.stream().filter(ts -> {
            boolean equalFFamily = fontFamily.equals(ts.getFONTFAMILY());
            boolean equalFSize = fontSize.equals(ts.getFONTSIZE());
            boolean equalFStyles = (fontStyles.isEmpty() && ts.getFONTSTYLE() == null) || (fontStyles.equals(ts.getFONTSTYLE()));
            return equalFFamily && equalFSize && equalFStyles;
        }).findFirst().orElse(null);
        if (textStyle == null) {
            textStyle = new TextStyle();
            textStyle.setFONTFAMILY(fontFamily);
            textStyle.setFONTSIZE(fontSize);
            if (!fontStyles.isEmpty()) {
                textStyle.setFONTSTYLE(fontStyles);
            }
            textStyle.setID("font" + textStyles.size());
            textStyles.add(textStyle);
        }
        return textStyle;
    }

    /**
     * A helper class which describes a single word. A word is constructed by abbyy {@link #addCharParam(CharParamsType)}.
     * Each char param describes a single character. 
     */
    private static class Word {

        private boolean space = false;

        private List<CharParamsType> charParams = new ArrayList<CharParamsType>();

        public Word(boolean isSpace) {
            this.space = isSpace;
        }

        /**
         * Adds a new character to this word.
         * 
         * @param charParam the new character
         */
        public void addCharParam(CharParamsType charParam) {
            this.charParams.add(charParam);
        }

        /**
         * Returns the surrounding rectangle for this word.
         * 
         * @return a rectangle containing all characters of this word
         */
        public Rectangle getRectangle() {
            Rectangle rect = new Rectangle();
            rect.left = charParams.stream().map(CharParamsType::getL).min(new BigIntegerComparator())
                .orElse(BigInteger.ZERO).intValue();
            rect.top = charParams.stream().map(CharParamsType::getT).min(new BigIntegerComparator())
                .orElse(BigInteger.ZERO).intValue();
            rect.right = charParams.stream().map(CharParamsType::getR).max(new BigIntegerComparator())
                .orElse(BigInteger.ZERO).intValue();
            rect.bottom = charParams.stream().map(CharParamsType::getB).max(new BigIntegerComparator())
                .orElse(BigInteger.ZERO).intValue();
            return rect;
        }

        /**
         * Returns the word as string.
         * 
         * @return the word as string
         */
        public String getValue() {
            return charParams.stream()
                             .flatMap(cp -> cp.getContent().stream())
                             .filter(c -> c instanceof String)
                             .map(String.class::cast)
                             .collect(Collectors.joining())
                             .trim();
        }

        /**
         * Confidence level of the OCR results for this word. A float value between 0 (unsure) and 1 (confident).
         * 
         * @return the word confidence.
         */
        public Float getWC() {
            boolean dictionaryCheckAvailable = !charParams.stream()
                                                          .map(CharParamsType::isWordFromDictionary)
                                                          .filter(dic -> dic == null)
                                                          .findAny()
                                                          .isPresent();
            if (dictionaryCheckAvailable) {
                return (float) charParams.stream().map(CharParamsType::isWordFromDictionary).mapToDouble(b -> {
                    return b ? 1d : 0d;
                }).average().orElse(0);
            }
            return getAverageCC();
        }

        /**
         * Confidence level of each character in that string. A list of numbers, one number between 0 (sure)
         * and 9 (unsure) for each character.
         * 
         * @return the character confidence as list if integers separated by spaces
         */
        public String getCC() {
            return charParams.stream().map(CharParamsType::getCharConfidence).map(BigInteger::intValue).map(cc -> {
                cc = cc == -1 ? 0 : cc;
                return String.valueOf((9 - Math.round((cc / 100f) * 9f)));
            }).collect(Collectors.joining(" "));
        }

        /**
         * Gets the average charConfidence of the word. 
         * 
         * @return the average CC
         */
        public Float getAverageCC() {
            Double wc = charParams.stream()
                                  .map(CharParamsType::getCharConfidence)
                                  .mapToInt(BigInteger::intValue)
                                  .average()
                                  .orElse(0);
            wc = wc == -1d ? 0 : wc;
            wc = wc / 100;
            return wc.floatValue();
        }
        
        /**
         * If this word is actually just a space character. This is used for convenience.
         * 
         * @return true if this word is a space character
         */
        public boolean isSpace() {
            return space;
        }

        private static class BigIntegerComparator implements Comparator<BigInteger> {
            @Override
            public int compare(BigInteger b1, BigInteger b2) {
                return b1.compareTo(b2);
            }
        }

    }

    /**
     * Simple helper class which represents a rectangle.
     */
    private static class Rectangle {
        int left, right, top, bottom;

        /**
         * Creates a new rectangle where left, right, top and bottom are -1.
         */
        public Rectangle() {
            this.left = -1;
            this.right = -1;
            this.top = -1;
            this.bottom = -1;
        }

        /**
         * Creates a new rectangle based on the dimensions of the given abbyy block.
         * 
         * @param abbyyBlock the abbyy block
         */
        public Rectangle(BlockType abbyyBlock) {
            this.bottom = abbyyBlock.getB().intValue();
            this.top = abbyyBlock.getT().intValue();
            this.left = abbyyBlock.getL().intValue();
            this.right = abbyyBlock.getR().intValue();
        }

        /**
         * Creates a new rectangle based on the dimensions of the given abbyy line.
         * 
         * @param abbyyLine the abbyy line
         */
        public Rectangle(LineType abbyyLine) {
            this.bottom = abbyyLine.getB().intValue();
            this.top = abbyyLine.getT().intValue();
            this.left = abbyyLine.getL().intValue();
            this.right = abbyyLine.getR().intValue();
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto block based on
         * this rectangle.
         * 
         * @param altoBlock the alto block where the rectangle is applied on
         */
        public void applyOnBlock(org.mycore.xml.alto.v2.BlockType altoBlock) {
            altoBlock.setHEIGHT(this.bottom - this.top);
            altoBlock.setWIDTH(this.right - this.left);
            altoBlock.setHPOS(this.left);
            altoBlock.setVPOS(this.top);
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto graphical element based on
         * this rectangle.
         * 
         * @param graphicalElement the alto graphical element where the rectangle is applied on
         */
        public void applyOnGraphicalElement(GraphicalElementType graphicalElement) {
            graphicalElement.setHEIGHT(this.bottom - this.top);
            graphicalElement.setWIDTH(this.right - this.left);
            graphicalElement.setHPOS(this.left);
            graphicalElement.setVPOS(this.top);
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto text line based on
         * this rectangle.
         * 
         * @param altoLine the alto text line where the rectangle is applied on
         */
        public void applyOnLine(TextLine altoLine) {
            altoLine.setHEIGHT(this.bottom - this.top);
            altoLine.setWIDTH(this.right - this.left);
            altoLine.setHPOS(this.left);
            altoLine.setVPOS(this.top);
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto page space based on
         * this rectangle.
         * 
         * @param altoPageSpace the alto page space where the rectangle is applied on
         */
        public void applyOnPageSpace(PageSpaceType altoPageSpace) {
            altoPageSpace.setHEIGHT(this.bottom - this.top);
            altoPageSpace.setWIDTH(this.right - this.left);
            altoPageSpace.setHPOS(this.left);
            altoPageSpace.setVPOS(this.top);
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto string based on
         * this rectangle.
         * 
         * @param string the alto string where the rectangle is applied on
         */
        public void applyOnString(StringType string) {
            string.setHEIGHT((float) this.bottom - this.top);
            string.setWIDTH((float) this.right - this.left);
            string.setHPOS((float) this.left);
            string.setVPOS((float) this.top);
        }

        /**
         * Sets the width, hpos and vpos of the given alto space based on this rectangle.
         * 
         * @param sp the alto space where the rectangle is applied on
         */
        public void applyOnSP(SP sp) {
            sp.setHPOS((float) this.left);
            sp.setVPOS((float) this.top);
            sp.setWIDTH((float) this.right - this.left);
        }

        /**
         * Tries to maximize the bounds of this rectangle. If the other rectangle is greater
         * on any side (left, top, right, bottom), this rectangle uses those sides. For left
         * and top values the lower value is used. For right and bottom the higher one.
         * 
         * @param other the other rectangle
         */
        public void maximize(Rectangle other) {
            this.left = (this.left == -1 || other.left < this.left) ? other.left : this.left;
            this.top = (this.top == -1 || other.top < this.top) ? other.top : this.top;
            this.right = (this.right == -1 || other.right > this.right) ? other.right : this.right;
            this.bottom = (this.bottom == -1 || other.bottom > this.bottom) ? other.bottom : this.bottom;
        }

        /**
         * Calculates the area of this rectangle
         * 
         * @return the area
         */
        public int area() {
            return (this.right - this.left) * (this.bottom - this.top);
        }

        @Override
        public String toString() {
            return "[left: " + left + " top: " + top + " right: " + right + " bottom: " + bottom + "]";
        }
    }

    /**
     * By default every string has its own font style. But in most cases each string in a line
     * has the same font. This method collects the font of each string per line and adds 
     * the most common font to the line.
     * 
     * @param pageSpace
     */
    private void optimizeFonts(PageSpaceType pageSpace) {
        Stream<ComposedBlockType> composedBlockStream = pageSpace.getContent().stream()
            .filter(block -> block instanceof ComposedBlockType).map(ComposedBlockType.class::cast);
        Stream<TextBlockType> textBlockStream = composedBlockStream.flatMap(cblock -> cblock.getContent().stream())
            .filter(block -> block instanceof TextBlockType).map(TextBlockType.class::cast);
        Stream<TextLine> lineStream = textBlockStream.flatMap(textBlock -> textBlock.getTextLine().stream());

        // move font's from string to line
        lineStream.forEach(line -> {
            // create string list
            Stream<StringType> stringStream = line.getStringAndSP().stream()
                .filter(object -> object instanceof StringType).map(StringType.class::cast);
            List<StringType> stringList = stringStream.collect(Collectors.toList());

            // create text style list
            Map<TextStyle, Long> styleCountMap = stringList.stream().flatMap(string -> string.getSTYLEREFS().stream())
                .filter(style -> style instanceof TextStyle).map(TextStyle.class::cast)
                .collect(Collectors.groupingBy(textStyle -> textStyle, Collectors.counting()));

            TextStyle mostCommonTextStyle = styleCountMap.entrySet().stream()
                .sorted(new Comparator<Map.Entry<TextStyle, Long>>() {
                    @Override
                    public int compare(Entry<TextStyle, Long> o1, Entry<TextStyle, Long> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                }).map(Entry::getKey).findFirst().orElse(null);

            line.getSTYLEREFS().add(mostCommonTextStyle);
            stringList.forEach(string -> {
                if (string.getSTYLEREFS().contains(mostCommonTextStyle)) {
                    string.setSTYLEREFS(null);
                }
            });
        });
    }

}
