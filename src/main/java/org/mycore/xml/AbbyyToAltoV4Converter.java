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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mycore.xml.abbyy.v10.*;
import org.mycore.xml.alto.v4.*;
import org.mycore.xml.alto.v4.ParagraphStyleType;
import org.mycore.xml.alto.v4.DescriptionType.Processing;
import org.mycore.xml.alto.v4.BlockType;

import javax.xml.bind.JAXBElement;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base class to convert a single abbyy xml file to an alto one. Just call {@link #convert(Document)}.
 * You can use {@link JAXBUtil#unmarshalAbbyyDocument(java.io.InputStream)} to load an abbyy document.
 *
 * @author Matthias Eichner
 */
public class AbbyyToAltoV4Converter {

    private static Logger LOGGER = LogManager.getLogger();

    private String defaultFontFamily = null;

    private Float defaultFontSize = null;

    private boolean enableConfidence = true;

    private static Map<String, String> LANG_MAPPING;

    private static final class IdGenerator {
        public enum Type {
            COMP_BLOCK,
            PAR,
            ILL,
            GRAPH,
            TABLE,
            LINE,
            STR,
            SP,
            GL
        }

        private Map<Type, AtomicInteger> ids;

        public IdGenerator() {
            ids = new HashMap<>(Type.values().length);
            for (int i = 0; i < Type.values().length; i++) {
                ids.put(Type.values()[i], new AtomicInteger(0));
            }
        }

        public int generateId(Type type) {
            return ids.get(type).incrementAndGet();
        }

        public String generateIdString(Type type) {
            return type.toString() + "_" + generateId(type);
        }
    }

    /**
     * Converts the given abbyy document to an ALTO one.
     *
     * @param abbyyDocument the abbyy document
     * @return alto xml as POJO
     */
    public AltoType convert(Document abbyyDocument) {
        IdGenerator idGenerator = new IdGenerator();
        AltoType alto = buildAlto();
        PageType page = buildAltoPage(alto);
        

        // Build Processing metadata from ABBYY producer
        String producer = abbyyDocument.getProducer();
        if (producer != null) {
            alto.getDescription().getProcessing().add(
                buildProcessing(producer)
            );
        }
        // Add Processing metadata for the conversion itself


        abbyyDocument.getPage().stream().findFirst().ifPresent(abbyyPage -> {
            page.setWIDTH(abbyyPage.getWidth().floatValue());
            page.setHEIGHT(abbyyPage.getHeight().floatValue());
        });
        PageSpaceType pageSpace = new PageSpaceType();
        page.setPrintSpace(pageSpace);

        Stream<org.mycore.xml.abbyy.v10.BlockType> blockStream = abbyyDocument.getPage().stream().flatMap(p -> p.getBlock().stream());

        Rectangle pageRect = new Rectangle();

        blockStream.forEach(abbyyBlock -> {
            String blockType = abbyyBlock.getBlockType();

            Rectangle blockRect = new Rectangle(abbyyBlock);
            pageRect.maximize(blockRect);

            if (blockType.equals("Text")) {
                ComposedBlockType composedBlock = new ComposedBlockType();
                abbyyBlock.getText().stream().flatMap(text -> text.getPar().stream()).forEach(abbyyParagraph -> {
                    handleParagraph(alto, composedBlock, abbyyParagraph, idGenerator);
                });
                if (composedBlock.getTextBlockOrIllustrationOrGraphicalElement().isEmpty()) {
                    return;
                }
                composedBlock.setTYPE("text");
                blockRect.applyOnBlock(composedBlock);
                composedBlock.setID(idGenerator.generateIdString(IdGenerator.Type.COMP_BLOCK));
                pageSpace.getTextBlockOrIllustrationOrGraphicalElement().add(composedBlock);
            } else if (blockType.equals("Picture")) {
                IllustrationType illustration = new IllustrationType();
                blockRect.applyOnBlock(illustration);
                illustration.setID(idGenerator.generateIdString(IdGenerator.Type.ILL));
                pageSpace.getTextBlockOrIllustrationOrGraphicalElement().add(illustration);
            } else if (blockType.equals("Table")) {
                ComposedBlockType tableBlock = new ComposedBlockType();
                tableBlock.setTYPE("table");
                blockRect.applyOnBlock(tableBlock);
                abbyyBlock.getRow().stream()
                        .flatMap(row -> row.getCell().stream())
                        .flatMap(cell -> cell.getText().stream())
                        .flatMap(text -> text.getPar().stream())
                        .forEach(abbyyParagraph -> {
                            handleParagraph(alto, tableBlock, abbyyParagraph, idGenerator);
                        });
                tableBlock.setID(idGenerator.generateIdString(IdGenerator.Type.TABLE));
                pageSpace.getTextBlockOrIllustrationOrGraphicalElement().add(tableBlock);
            } else if (blockType.equals("Separator") || blockType.equals("SeparatorsBox")) {
                GraphicalElementType graphicalSeparator = new GraphicalElementType();
                blockRect.applyOnGraphicalElement(graphicalSeparator);
                graphicalSeparator.setID(idGenerator.generateIdString(IdGenerator.Type.GRAPH));
                pageSpace.getTextBlockOrIllustrationOrGraphicalElement().add(graphicalSeparator);
            } else if (blockType.equals("Barcode")) {
                LOGGER.warn("Unsupported block type '" + blockType + "' at " + blockRect);
            } else {
                throw new ConvertException("Invalid block type " + blockType + " at " + blockRect);
            }
        });

        pageRect.applyOnPageSpace(pageSpace);
        optimizeFonts(pageSpace);
        updateParagraphStyles(alto.getStyles().getParagraphStyle());
        return alto;
    }

    /**
     * Builds an empty {@link AltoType} object with description and styles.
     *
     * @return alto as java object
     */
    private AltoType buildAlto() {
        AltoType alto = new AltoType();
        alto.setDescription(buildDescription(alto));
        alto.setStyles(new StylesType());
        return alto;
    }

    private DescriptionType buildDescription(AltoType alto) {
        DescriptionType description = new DescriptionType();
        description.setMeasurementUnit(MeasurementUnitType.PIXEL);
        return description;
    }

    private PageType buildAltoPage(AltoType alto) {
        LayoutType layout = new LayoutType();
        alto.setLayout(layout);
        PageType page = new PageType();
        layout.getPage().add(page);
        page.setID("Page");
        return page;
    }

    private Processing buildProcessing(String producer) {
        ProcessingSoftwareType processingSoftware = new ProcessingSoftwareType();
        processingSoftware.setSoftwareName(producer);

        Processing processingStep = new Processing();
        processingStep.setProcessingSoftware(processingSoftware);

        return processingStep;
    }

    /**
     * Handles the convert process of one abbyy paragraph.
     *
     * @param alto           the alto document
     * @param composedBlock  the parent alto composed block
     * @param abbyyParagraph the source abbyy paragraph
     * @param idGenerator    an count of how many paragraphs are already added, used for id generation of
     */
    private void handleParagraph(AltoType alto, ComposedBlockType composedBlock, ParagraphType abbyyParagraph,
                                 IdGenerator idGenerator) {
        TextBlockType paragraphBlock = new TextBlockType();
        Rectangle textBlockRect = new Rectangle();
        if (abbyyParagraph.getLine().isEmpty()) {
            return;
        }
        abbyyParagraph.getLine().forEach(abbyyLine -> {
            handleLine(alto, paragraphBlock, textBlockRect, abbyyLine, idGenerator);
        });
        if (paragraphBlock.getTextLine().isEmpty()) {
            return;
        }
        textBlockRect.applyOnBlock(paragraphBlock);
        paragraphBlock.setID(idGenerator.generateIdString(IdGenerator.Type.PAR));
        ParagraphStyleType paragraphStyle = getParagraphStyle(alto.getStyles(), abbyyParagraph);
        if (paragraphStyle != null) {
            paragraphBlock.getSTYLEREFS().add(paragraphStyle);
        }
        composedBlock.getTextBlockOrIllustrationOrGraphicalElement().add(paragraphBlock);
    }

    /**
     * Handles the convert process of one abbyy text line.
     *
     * @param alto          the alto document
     * @param textBlock     the alto text blocl
     * @param textBlockRect an rectangle with the bounds of the alto block
     * @param abbyyLine     the source abbyy text line
     * @param idGenerator   the ID generator
     */
    private void handleLine(AltoType alto, TextBlockType textBlock, Rectangle textBlockRect, LineType abbyyLine, IdGenerator idGenerator) {
        Rectangle lineRect = new Rectangle(abbyyLine);
        if (lineRect.area() == 0) {
            return;
        }
        textBlockRect.maximize(lineRect);
        TextBlockType.TextLine altoLine = new TextBlockType.TextLine();
        altoLine.setID(idGenerator.generateIdString(IdGenerator.Type.LINE));
        lineRect.applyOnLine(altoLine);

        Map<String, Integer> langCounts = new HashMap<>();
        abbyyLine.getFormatting().forEach(abbyyFormatting -> {
            handleFormatting(alto, altoLine, abbyyFormatting, idGenerator);
            langCounts.put(abbyyFormatting.getLang(), langCounts.getOrDefault(abbyyFormatting.getLang(), 0) + 1);
        });

        String lang = Collections.max(langCounts.entrySet(), Comparator.comparingInt(Map.Entry::getValue)).getKey();
        altoLine.setLANG(LANG_MAPPING.get(lang));

        if (ignoreAltoLine(altoLine)) {
            return;
        }
        textBlock.getTextLine().add(altoLine);
    }

    /**
     * Checks if the given alto line should be ignored and not added to its corresponding block.
     * By default this method checks if the alto line is empty or contains just white spaces.
     *
     * @param altoLine the alto line to check
     * @return true if the line should be ignored, otherwise false
     */
    protected boolean ignoreAltoLine(TextBlockType.TextLine altoLine) {
        for (Object content : altoLine.getStringAndSP()) {
            // due to the fact that only SP and String are allowed we can just check
            // the instance type of the content and if its a string the line should'nt
            // be ignored
            if (content instanceof StringType) {
                return false;
            }
        }
        return true;
    }

    /**
     * Handles the convert process of one abbyy formatting.
     *
     * @param alto            the alto document
     * @param altoLine        the alto line where the abbyy formatting is added to
     * @param abbyyFormatting the source abbyy formatting
     * @param idGenerator     the ID generator
     */
    private void handleFormatting(AltoType alto, TextBlockType.TextLine altoLine, FormattingType abbyyFormatting, IdGenerator idGenerator) {
        // get the text style for this formatting
        TextStyleType style = getTextStyle(alto.getStyles(), abbyyFormatting);

        // get the characters of the formatting
        Stream<CharParamsType> charParamsStream = abbyyFormatting.getContent().stream().filter(c -> {
            return c instanceof JAXBElement && ((JAXBElement<?>) c).getValue() instanceof CharParamsType;
        }).map(c -> (CharParamsType) ((JAXBElement<?>) c).getValue());

        // build the word list
        List<Word> words = new ArrayList<>();
        AtomicReference<Word> wordRef = new AtomicReference<>();
        charParamsStream.forEach(charParam -> {
            String value = charParam.getContent()
                    .stream()
                    .filter(c -> c instanceof String)
                    .map(String.class::cast)
                    .collect(Collectors.joining())
                    .trim();
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
        addToLine(altoLine, style, words, idGenerator);
    }

    /**
     * Adds each word of the word list to the line with the given text style.
     *
     * @param altoLine    the alto line where the words are added to
     * @param style       the text style of the line
     * @param words       a list of words
     * @param idGenerator the ID generator
     */
    private void addToLine(TextBlockType.TextLine altoLine, TextStyleType style, List<Word> words, IdGenerator idGenerator) {
        words.forEach(word -> {
            Rectangle wordRect = word.getRectangle();
            if (word.isSpace()) {
                SPType sp = new SPType();
                sp.setID(idGenerator.generateIdString(IdGenerator.Type.SP));
                wordRect.applyOnSP(sp);
                altoLine.getStringAndSP().add(sp);
            } else {
                StringType string = new StringType();
                string.setID(idGenerator.generateIdString(IdGenerator.Type.STR));
                string.setCONTENT(word.getValue());
                addGlyphs(string, word, idGenerator);
                if (enableConfidence) {
                    try {
                        string.setWC(word.getWC());
                    } catch (Exception exc) {
                        LOGGER.warn("Error while getting word confidence (WC) of " + word.getValue());
                        string.setWC(0f);
                    }
                    try {
                        string.setCC(word.getCC());
                    } catch (Exception exc) {
                        LOGGER.warn("Error while getting character confidence (CC) of " + word.getValue());
                    }
                }
                string.getSTYLEREFS().add(style);
                wordRect.applyOnString(string);

                altoLine.getStringAndSP().add(string);
            }
        });
    }

    private void addGlyphs(StringType string, Word word, IdGenerator idGenerator) {
        word.charParams.forEach(ch -> {
            GlyphType glyph = new GlyphType();
            glyph.setID(idGenerator.generateIdString(IdGenerator.Type.GL));

            glyph.setCONTENT(ch.getContent().stream()
                    .filter(c -> c instanceof String)
                    .map(String.class::cast)
                    .collect(Collectors.joining())
                    .trim());
            glyph.setHPOS(ch.getL().floatValue());
            glyph.setVPOS(ch.getT().floatValue());
            glyph.setWIDTH(ch.getR().subtract(ch.getL()).floatValue());
            glyph.setHEIGHT(ch.getB().subtract(ch.getT()).floatValue());
            if (ch.getCharConfidence() != null) {
                glyph.setGC(ch.getCharConfidence().intValue() / 100f);
            }

            string.getGlyph().add(glyph);
        });
    }

    /**
     * Gets the alto {@link TextStyleType} by the abbyy formatting.
     *
     * @param styles          the alto styles
     * @param abbyyFormatting the abbyy formatting
     * @return the alto text style object
     */
    private TextStyleType getTextStyle(StylesType styles, FormattingType abbyyFormatting) {
        String fontFamily = abbyyFormatting.getFf() != null ? abbyyFormatting.getFf() : getDefaultFontFamily();
        if (fontFamily == null) {
            throw new ConvertException("Unable to set font familiy of TextStyle cause the ff attribute of a formatting "
                    + " element is missing. There is no default font family configured. Please use setDefaultFontFamily(string) to "
                    + "fix this!");
        }
        Float fontSize = abbyyFormatting.getFs() != null ? abbyyFormatting.getFs() : getDefaultFontSize();
        if (fontSize == null) {
            throw new ConvertException("Unable to set font size of TextStyle cause the fs attribute of a formatting "
                    + "element is missing. There is no default font size configured. Please use setDefaultFontSize(float) to "
                    + "fix this!");
        }

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
     * Gets the {@link TextStyleType} for the given font family and size. Each alto document
     * have a pre defined list of fonts. If no text style is found, this method creates
     * an appropriate one.
     *
     * @param styles     the alto styles
     * @param fontFamily the font family
     * @param fontSize   the font size
     * @param fontStyles list of font styles e.g. bold or italics
     * @return the text style
     */
    private TextStyleType getTextStyle(StylesType styles, String fontFamily, Float fontSize, List<String> fontStyles) {
        List<TextStyleType> textStyles = styles.getTextStyle();
        TextStyleType textStyle = textStyles.stream().filter(ts -> {
            boolean equalFFamily = fontFamily.equals(ts.getFONTFAMILY());
            boolean equalFSize = fontSize.equals(ts.getFONTSIZE());
            boolean equalFStyles = (fontStyles.isEmpty() && ts.getFONTSTYLE() == null)
                    || (fontStyles.equals(ts.getFONTSTYLE()));
            return equalFFamily && equalFSize && equalFStyles;
        }).findFirst().orElse(null);
        if (textStyle == null) {
            textStyle = new TextStyleType();
            textStyle.setFONTFAMILY(fontFamily);
            textStyle.setFONTSIZE(fontSize);
            if (!fontStyles.isEmpty()) {
                textStyle.getFONTSTYLE().clear();
                textStyle.getFONTSTYLE().addAll(fontStyles);
            }
            textStyle.setID("font" + textStyles.size());
            textStyles.add(textStyle);
        }
        return textStyle;
    }

    /**
     * <p>Returns a new or existing paragraph style of a given abbbyy par element.
     * If the paragraph style didn't exists yet, this method will add it to
     * the style element too.</p>
     * <p>If the par element doesn't has any attributes, this method will return
     * null</p>
     *
     * @param styles    the existing styles
     * @param paragraph the abbyy par element
     * @return a alto paragraph style
     */
    private ParagraphStyleType getParagraphStyle(StylesType styles, ParagraphType paragraph) {
        ParagraphStyleType paragraphStyle = createParagraphStyle(paragraph);
        if (paragraphStyle == null) {
            return null;
        }
        List<ParagraphStyleType> paragraphStyles = styles.getParagraphStyle();
        return paragraphStyles.stream()
                .filter(ps -> Objects.equals(ps.getALIGN(), paragraphStyle.getALIGN())
                        && Objects.equals(ps.getLEFT(), paragraphStyle.getLEFT())
                        && Objects.equals(ps.getRIGHT(), paragraphStyle.getRIGHT())
                        && Objects.equals(ps.getLINESPACE(), paragraphStyle.getLINESPACE())
                        && Objects.equals(ps.getFIRSTLINE(), paragraphStyle.getFIRSTLINE()))
                .findFirst()
                .orElseGet(() -> {
                    styles.getParagraphStyle().add(paragraphStyle);
                    return paragraphStyle;
                });
    }

    /**
     * Creates a new alto paragraph style based on the given attributes
     * of the abbyy par element. The ID of the ParagraphStyle is null!
     *
     * @param paragraph the abbyy par element
     * @return a new alto paragraph style
     */
    private ParagraphStyleType createParagraphStyle(ParagraphType paragraph) {
        ParagraphStyleType ps = new ParagraphStyleType();
        ParagraphAlignment align = paragraph.getAlign();
        boolean hasAttributes = false;
        if (!align.equals(ParagraphAlignment.LEFT)) {
            String alignValue = align.name().toLowerCase();
            alignValue = alignValue.equals("justified") ? "block" : alignValue;
            ps.setALIGN(alignValue.substring(0, 1).toUpperCase() + alignValue.substring(1));
            hasAttributes = true;
        }
        if (!paragraph.getLeftIndent().equals(new BigInteger("0"))) {
            ps.setLEFT(paragraph.getLeftIndent().floatValue());
            hasAttributes = true;
        }
        if (!paragraph.getRightIndent().equals(new BigInteger("0"))) {
            ps.setRIGHT(paragraph.getRightIndent().floatValue());
            hasAttributes = true;
        }
        if (!paragraph.getLineSpacing().equals(new BigInteger("0"))) {
            ps.setLINESPACE(paragraph.getLineSpacing().floatValue());
            hasAttributes = true;
        }
        if (!paragraph.getStartIndent().equals(new BigInteger("0"))) {
            ps.setFIRSTLINE(paragraph.getStartIndent().floatValue());
            hasAttributes = true;
        }
        return hasAttributes ? ps : null;
    }

    /**
     * A helper class which describes a single word. A word is constructed by abbyy {@link #addCharParam(CharParamsType)}.
     * Each char param describes a single character.
     */
    private static class Word {

        private boolean space = false;

        private List<CharParamsType> charParams = new ArrayList<>();

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
            rect.left = charParams.stream()
                    .map(CharParamsType::getL)
                    .min(new BigIntegerComparator())
                    .orElse(BigInteger.ZERO)
                    .intValue();
            rect.top = charParams.stream()
                    .map(CharParamsType::getT)
                    .min(new BigIntegerComparator())
                    .orElse(BigInteger.ZERO)
                    .intValue();
            rect.right = charParams.stream()
                    .map(CharParamsType::getR)
                    .max(new BigIntegerComparator())
                    .orElse(BigInteger.ZERO)
                    .intValue();
            rect.bottom = charParams.stream()
                    .map(CharParamsType::getB)
                    .max(new BigIntegerComparator())
                    .orElse(BigInteger.ZERO)
                    .intValue();
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
                return (float) charParams.stream()
                        .map(CharParamsType::isWordFromDictionary)
                        .mapToDouble(b -> b ? 1d : 0d)
                        .average()
                        .orElse(0);
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
        float left, right, top, bottom;

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
        public Rectangle(org.mycore.xml.abbyy.v10.BlockType abbyyBlock) {
            this.bottom = abbyyBlock.getB().floatValue();
            this.top = abbyyBlock.getT().floatValue();
            this.left = abbyyBlock.getL().floatValue();
            this.right = abbyyBlock.getR().floatValue();
        }

        /**
         * Creates a new rectangle based on the dimensions of the given abbyy line.
         *
         * @param abbyyLine the abbyy line
         */
        public Rectangle(LineType abbyyLine) {
            this.bottom = abbyyLine.getB().floatValue();
            this.top = abbyyLine.getT().floatValue();
            this.left = abbyyLine.getL().floatValue();
            this.right = abbyyLine.getR().floatValue();
        }

        /**
         * Sets the height, width, hpos and vpos of the given alto block based on
         * this rectangle.
         *
         * @param altoBlock the alto block where the rectangle is applied on
         */
        public void applyOnBlock(BlockType altoBlock) {
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
        public void applyOnLine(TextBlockType.TextLine altoLine) {
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
            string.setHEIGHT(this.bottom - this.top);
            string.setWIDTH(this.right - this.left);
            string.setHPOS(this.left);
            string.setVPOS(this.top);
        }

        /**
         * Sets the width, hpos and vpos of the given alto space based on this rectangle.
         *
         * @param sp the alto space where the rectangle is applied on
         */
        public void applyOnSP(SPType sp) {
            sp.setHPOS(this.left);
            sp.setVPOS(this.top);
            sp.setWIDTH(this.right - this.left);
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
        public float area() {
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
     * @param pageSpace the page space to optimize
     */
    private void optimizeFonts(PageSpaceType pageSpace) {
        Stream<ComposedBlockType> composedBlockStream = pageSpace.getTextBlockOrIllustrationOrGraphicalElement().stream()
                .filter(block -> block instanceof ComposedBlockType).map(ComposedBlockType.class::cast);
        Stream<TextBlockType> textBlockStream = composedBlockStream.flatMap(cblock -> cblock.getTextBlockOrIllustrationOrGraphicalElement().stream())
                .filter(block -> block instanceof TextBlockType).map(TextBlockType.class::cast);
        Stream<TextBlockType.TextLine> lineStream = textBlockStream.flatMap(textBlock -> textBlock.getTextLine().stream());

        // move font's from string to line
        lineStream.forEach(line -> {
            // create string list
            Stream<StringType> stringStream = line.getStringAndSP().stream()
                    .filter(object -> object instanceof StringType).map(StringType.class::cast);
            List<StringType> stringList = stringStream.collect(Collectors.toList());

            // create text style list
            Map<TextStyleType, Long> styleCountMap = stringList.stream().flatMap(string -> string.getSTYLEREFS().stream())
                    .filter(style -> style instanceof TextStyleType).map(TextStyleType.class::cast)
                    .collect(Collectors.groupingBy(textStyle -> textStyle, Collectors.counting()));

            TextStyleType mostCommonTextStyle = styleCountMap.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .map(Entry::getKey)
                    .findFirst()
                    .orElse(null);

            line.getSTYLEREFS().add(mostCommonTextStyle);
            stringList.forEach(string -> {
                if (string.getSTYLEREFS().contains(mostCommonTextStyle)) {
                    string.getSTYLEREFS().clear();
                }
            });
        });
    }

    /**
     * Add a ID attribute to each paragraph style, its required.
     *
     * @param styles the paragraph styles
     */
    private void updateParagraphStyles(List<ParagraphStyleType> styles) {
        for (int i = 0; i < styles.size(); i++) {
            styles.get(i).setID("paragraph" + i);
        }
    }

    /**
     * Returns the default font family or null if nothing is set explicit.
     *
     * @return the default font size
     */
    public String getDefaultFontFamily() {
        return defaultFontFamily;
    }

    /**
     * Sets the default font family for an abbyy formatting element if
     * there is no ff attribute specified.
     *
     * @param defaultFontFamily the default font family to use
     */
    public void setDefaultFontFamily(String defaultFontFamily) {
        this.defaultFontFamily = defaultFontFamily;
    }

    /**
     * Returns the default font size or null if nothing is set explicit.
     *
     * @return the default font size
     */
    public Float getDefaultFontSize() {
        return defaultFontSize;
    }

    /**
     * Sets the default font size for an abbyy formatting element if
     * there is no fs attribute specified.
     *
     * @param defaultFontSize the default font size to use
     */
    public void setDefaultFontSize(Float defaultFontSize) {
        this.defaultFontSize = defaultFontSize;
    }

    /**
     * Checks if the WC and CC attributes of the ALTO should be calculated and applied.
     * By default the confidence attributes are enabled.
     *
     * @return true if they are added to the ALTO document, false otherwise
     */
    public boolean isConfidenceEnabled() {
        return this.enableConfidence;
    }

    /**
     * If the confidence attributes WC and CC should be calculated and applied.
     *
     * @param enableConfidence true = the WC and CC attributes are added, false = they are ignored
     */
    public void setEnableConfidence(boolean enableConfidence) {
        this.enableConfidence = enableConfidence;
    }

    static {
        LANG_MAPPING = new HashMap<>();
        LANG_MAPPING.put("Abkhaz", null);
        LANG_MAPPING.put("Adyghe", null);
        LANG_MAPPING.put("Afrikaans", "af");
        LANG_MAPPING.put("Agul", null);
        LANG_MAPPING.put("Albanian", "sq");
        LANG_MAPPING.put("Altaic", null);
        LANG_MAPPING.put("ArabicAlgeria", "ar-DZ");
        LANG_MAPPING.put("ArabicBahrain", "ar-BH");
        LANG_MAPPING.put("ArabicEgypt", "ar-EG");
        LANG_MAPPING.put("ArabicIraq", "ar-IQ");
        LANG_MAPPING.put("ArabicJordan", "ar-JO");
        LANG_MAPPING.put("ArabicKuwait", "ar-KW");
        LANG_MAPPING.put("ArabicLebanon", "ar-LB");
        LANG_MAPPING.put("ArabicLibya", "ar-LY");
        LANG_MAPPING.put("ArabicMorocco", "ar-MA");
        LANG_MAPPING.put("ArabicOman", "ar-OM");
        LANG_MAPPING.put("ArabicQatar", "ar-QA");
        LANG_MAPPING.put("ArabicSaudiArabia", "ar-SA");
        LANG_MAPPING.put("ArabicSyria", "ar-SY");
        LANG_MAPPING.put("ArabicTunisia", "ar-TN");
        LANG_MAPPING.put("ArabicUAE", "ar-AE");
        LANG_MAPPING.put("ArabicYemen", "ar-YE");
        LANG_MAPPING.put("ArmenianEastern", null);
        LANG_MAPPING.put("ArmenianGrabar", null);
        LANG_MAPPING.put("ArmenianWestern", null);
        LANG_MAPPING.put("Awar", null);
        LANG_MAPPING.put("Aymara", null);
        LANG_MAPPING.put("AzeriCyrillic", null);
        LANG_MAPPING.put("AzeriLatin", null);
        LANG_MAPPING.put("Bashkir", null);
        LANG_MAPPING.put("Basque", "eu");
        LANG_MAPPING.put("Belarusian", "be");
        LANG_MAPPING.put("Bemba", null);
        LANG_MAPPING.put("Blackfoot", null);
        LANG_MAPPING.put("Breton", null);
        LANG_MAPPING.put("Bugotu", null);
        LANG_MAPPING.put("Bulgarian", "bg");
        LANG_MAPPING.put("Burmese", null);
        LANG_MAPPING.put("Buryat", null);
        LANG_MAPPING.put("Catalan", "ca");
        LANG_MAPPING.put("Chamorro", null);
        LANG_MAPPING.put("Chechen", null);
        LANG_MAPPING.put("ChineseHongKong", "zh-HK");
        LANG_MAPPING.put("ChineseMacau", null);
        LANG_MAPPING.put("ChinesePRC", "zh-CN");
        LANG_MAPPING.put("ChineseSingapore", "zh-SG");
        LANG_MAPPING.put("ChineseTaiwan", "zh-TW");
        LANG_MAPPING.put("Chukcha", null);
        LANG_MAPPING.put("Chuvash", null);
        LANG_MAPPING.put("Corsican", null);
        LANG_MAPPING.put("CrimeanTatar", null);
        LANG_MAPPING.put("Croatian", "hr");
        LANG_MAPPING.put("Crow", null);
        LANG_MAPPING.put("Czech", "cz");
        LANG_MAPPING.put("Danish", "da");
        LANG_MAPPING.put("Dargwa", null);
        LANG_MAPPING.put("Dungan", null);
        LANG_MAPPING.put("DutchBelgian", "nl-BE");
        LANG_MAPPING.put("DutchStandard", "nl");
        LANG_MAPPING.put("EnglishAustralian", "en-AU");
        LANG_MAPPING.put("EnglishBelize", "en-BZ");
        LANG_MAPPING.put("EnglishCanadian", "en-CA");
        LANG_MAPPING.put("EnglishCaribbean", "en-BQ");
        LANG_MAPPING.put("EnglishIreland", "en-IE");
        LANG_MAPPING.put("EnglishJamaica", "en-JM");
        LANG_MAPPING.put("EnglishLaw", null);
        LANG_MAPPING.put("EnglishMedical", null);
        LANG_MAPPING.put("EnglishNewZealand", "en-NZ");
        LANG_MAPPING.put("EnglishPhilippines", null);
        LANG_MAPPING.put("EnglishSouthAfrica", "en-ZA");
        LANG_MAPPING.put("EnglishTrinidad", "en-TT");
        LANG_MAPPING.put("EnglishUnitedKingdom", "en-GB");
        LANG_MAPPING.put("EnglishUnitedStates", "en-US");
        LANG_MAPPING.put("EnglishZimbabwe", null);
        LANG_MAPPING.put("EskimoCyrillic", null);
        LANG_MAPPING.put("EskimoLatin", null);
        LANG_MAPPING.put("Esperanto", null);
        LANG_MAPPING.put("Estonian", "et");
        LANG_MAPPING.put("Even", null);
        LANG_MAPPING.put("Evenki", null);
        LANG_MAPPING.put("Faeroese", "fo");
        LANG_MAPPING.put("Farsi", "fa");
        LANG_MAPPING.put("Fijian", null);
        LANG_MAPPING.put("Finnish", "fi");
        LANG_MAPPING.put("FrenchBelgian", "fr-BE");
        LANG_MAPPING.put("FrenchCanadian", "fr-CA");
        LANG_MAPPING.put("FrenchLuxembourg", "fr-LU");
        LANG_MAPPING.put("FrenchMonaco", "fr-MC");
        LANG_MAPPING.put("FrenchStandard", "fr");
        LANG_MAPPING.put("FrenchSwiss", "fr-CH");
        LANG_MAPPING.put("Frisian", null);
        LANG_MAPPING.put("Friulian", null);
        LANG_MAPPING.put("GaelicScottish", "gd");
        LANG_MAPPING.put("Gagauz", null);
        LANG_MAPPING.put("Galician", null);
        LANG_MAPPING.put("Ganda", null);
        LANG_MAPPING.put("Georgian", null);
        LANG_MAPPING.put("GermanAustrian", "de-AT");
        LANG_MAPPING.put("GermanLaw", "de");
        LANG_MAPPING.put("GermanLiechtenstein", "de-LI");
        LANG_MAPPING.put("GermanLuxembourg", "de-LU");
        LANG_MAPPING.put("GermanMedical", "de");
        LANG_MAPPING.put("GermanNewSpelling", "de");
        LANG_MAPPING.put("GermanNewSpellingLaw", "de");
        LANG_MAPPING.put("GermanNewSpellingMedical", "de");
        LANG_MAPPING.put("GermanStandard", "de");
        LANG_MAPPING.put("GermanSwiss", "de-CH");
        LANG_MAPPING.put("Greek", "el");
        LANG_MAPPING.put("GreekKathareusa", "el");
        LANG_MAPPING.put("Guarani", null);
        LANG_MAPPING.put("Hani", null);
        LANG_MAPPING.put("Hausa", null);
        LANG_MAPPING.put("Hawaiian", null);
        LANG_MAPPING.put("Hebrew", "he");
        LANG_MAPPING.put("Hungarian", "hu");
        LANG_MAPPING.put("Icelandic", "is");
        LANG_MAPPING.put("Ido", null);
        LANG_MAPPING.put("Indonesian", "id");
        LANG_MAPPING.put("Ingush", null);
        LANG_MAPPING.put("Interlingua", null);
        LANG_MAPPING.put("Irish", "ga");
        LANG_MAPPING.put("ItalianStandard", "it");
        LANG_MAPPING.put("ItalianSwiss", "it-CH");
        LANG_MAPPING.put("Japanese", "ja");
        LANG_MAPPING.put("Kabardian", null);
        LANG_MAPPING.put("Kalmyk", null);
        LANG_MAPPING.put("KarachayBalkar", null);
        LANG_MAPPING.put("Karakalpak", null);
        LANG_MAPPING.put("Kasub", null);
        LANG_MAPPING.put("Kawa", null);
        LANG_MAPPING.put("Kazakh", null);
        LANG_MAPPING.put("Khakas", null);
        LANG_MAPPING.put("Khanty", null);
        LANG_MAPPING.put("Kikuyu", null);
        LANG_MAPPING.put("Kirgiz", null);
        LANG_MAPPING.put("Kongo", null);
        LANG_MAPPING.put("Korean", "ko");
        LANG_MAPPING.put("KoreanJohab", "ko");
        LANG_MAPPING.put("Koryak", null);
        LANG_MAPPING.put("Kpelle", null);
        LANG_MAPPING.put("Kumyk", null);
        LANG_MAPPING.put("Kurdish", "ku");
        LANG_MAPPING.put("Lak", null);
        LANG_MAPPING.put("Lappish", null);
        LANG_MAPPING.put("Latin", null);
        LANG_MAPPING.put("Latvian", "lv");
        LANG_MAPPING.put("LatvianGothic", null);
        LANG_MAPPING.put("Lezgin", null);
        LANG_MAPPING.put("Lithuanian", "lt");
        LANG_MAPPING.put("LithuanianClassic", null);
        LANG_MAPPING.put("Luba", null);
        LANG_MAPPING.put("Macedonian", "mk");
        LANG_MAPPING.put("Malagasy", null);
        LANG_MAPPING.put("MalayBruneiDarussalam", "ml");
        LANG_MAPPING.put("MalayMalaysian", "ms");
        LANG_MAPPING.put("Malinke", null);
        LANG_MAPPING.put("Maltese", "mt");
        LANG_MAPPING.put("Mansi", null);
        LANG_MAPPING.put("Maori", null);
        LANG_MAPPING.put("Mari", null);
        LANG_MAPPING.put("Maya", null);
        LANG_MAPPING.put("Miao", null);
        LANG_MAPPING.put("Minankabaw", null);
        LANG_MAPPING.put("Mohawk", null);
        LANG_MAPPING.put("Mongol", null);
        LANG_MAPPING.put("Mordvin", null);
        LANG_MAPPING.put("Nahuatl", null);
        LANG_MAPPING.put("Nenets", null);
        LANG_MAPPING.put("Nivkh", null);
        LANG_MAPPING.put("Nogay", null);
        LANG_MAPPING.put("NorwegianBokmal", "nb");
        LANG_MAPPING.put("NorwegianNynorsk", "nn");
        LANG_MAPPING.put("Nyanja", null);
        LANG_MAPPING.put("Occidental", null);
        LANG_MAPPING.put("Ojibway", null);
        LANG_MAPPING.put("OldEnglish", null);
        LANG_MAPPING.put("OldFrench", null);
        LANG_MAPPING.put("OldGerman", null);
        LANG_MAPPING.put("OldItalian", null);
        LANG_MAPPING.put("OldSpanish", null);
        LANG_MAPPING.put("Ossetic", null);
        LANG_MAPPING.put("Papiamento", null);
        LANG_MAPPING.put("PidginEnglish", null);
        LANG_MAPPING.put("Polish", "pl");
        LANG_MAPPING.put("PortugueseBrazilian", "pt-BR");
        LANG_MAPPING.put("PortugueseStandard", "pt");
        LANG_MAPPING.put("Provencal", null);
        LANG_MAPPING.put("Quechua", null);
        LANG_MAPPING.put("RhaetoRomanic", "rm");
        LANG_MAPPING.put("Romanian", "ro");
        LANG_MAPPING.put("RomanianMoldavia", "ro-MD");
        LANG_MAPPING.put("Romany", null);
        LANG_MAPPING.put("Ruanda", null);
        LANG_MAPPING.put("Rundi", null);
        LANG_MAPPING.put("Russian", "ru");
        LANG_MAPPING.put("RussianMoldavia", "ru-MD");
        LANG_MAPPING.put("RussianOldSpelling", "ru");
        LANG_MAPPING.put("Samoan", null);
        LANG_MAPPING.put("Selkup", null);
        LANG_MAPPING.put("SerbianCyrillic", "sr");
        LANG_MAPPING.put("SerbianLatin", "sr");
        LANG_MAPPING.put("Shona", null);
        LANG_MAPPING.put("Sioux", null);
        LANG_MAPPING.put("Slovak", "sk");
        LANG_MAPPING.put("Slovenian", "sl");
        LANG_MAPPING.put("Somali", null);
        LANG_MAPPING.put("Sorbian", "sb");
        LANG_MAPPING.put("Sotho", null);
        LANG_MAPPING.put("SpanishArgentina", "es-AR");
        LANG_MAPPING.put("SpanishBolivia", "es-BO");
        LANG_MAPPING.put("SpanishChile", "es-CL");
        LANG_MAPPING.put("SpanishColombia", "es-CO");
        LANG_MAPPING.put("SpanishCostaRica", "es-CO");
        LANG_MAPPING.put("SpanishDominicanRepublic", "es-DO");
        LANG_MAPPING.put("SpanishEcuador", "es-EC");
        LANG_MAPPING.put("SpanishElSalvador", "es-SV");
        LANG_MAPPING.put("SpanishGuatemala", "es-GT");
        LANG_MAPPING.put("SpanishHonduras", "es-HN");
        LANG_MAPPING.put("SpanishMexican", "es-MX");
        LANG_MAPPING.put("SpanishModernSort", "es");
        LANG_MAPPING.put("SpanishNicaragua", "es-NI");
        LANG_MAPPING.put("SpanishPanama", "es-PA");
        LANG_MAPPING.put("SpanishParaguay", "es-PY");
        LANG_MAPPING.put("SpanishPeru", "es-PE");
        LANG_MAPPING.put("SpanishPuertoRico", "es-PR");
        LANG_MAPPING.put("SpanishTraditionalSort", "es");
        LANG_MAPPING.put("SpanishUruguay", "es-UY");
        LANG_MAPPING.put("SpanishVenezuela", "es-VE");
        LANG_MAPPING.put("Sunda", null);
        LANG_MAPPING.put("Swahili", null);
        LANG_MAPPING.put("Swazi", null);
        LANG_MAPPING.put("Swedish", "sv");
        LANG_MAPPING.put("SwedishFinland", "sv-FI");
        LANG_MAPPING.put("Tabassaran", null);
        LANG_MAPPING.put("Tagalog", null);
        LANG_MAPPING.put("Tahitian", null);
        LANG_MAPPING.put("Tajik", null);
        LANG_MAPPING.put("Tatar", null);
        LANG_MAPPING.put("Thai", "th");
        LANG_MAPPING.put("Tinpo", null);
        LANG_MAPPING.put("Tongan", null);
        LANG_MAPPING.put("Tswana", "tn");
        LANG_MAPPING.put("Tun", null);
        LANG_MAPPING.put("Turkish", "tr");
        LANG_MAPPING.put("Turkmen", null);
        LANG_MAPPING.put("TurkmenLatin", null);
        LANG_MAPPING.put("Tuvin", null);
        LANG_MAPPING.put("Udmurt", null);
        LANG_MAPPING.put("UighurCyrillic", null);
        LANG_MAPPING.put("UighurLatin", null);
        LANG_MAPPING.put("Ukrainian", "uk");
        LANG_MAPPING.put("User", null);
        LANG_MAPPING.put("UzbekCyrillic", null);
        LANG_MAPPING.put("UzbekLatin", null);
        LANG_MAPPING.put("Vietnamese", "vi");
        LANG_MAPPING.put("Visayan", null);
        LANG_MAPPING.put("Welsh", "cy");
        LANG_MAPPING.put("Wolof", null);
        LANG_MAPPING.put("Xhosa", "xh");
        LANG_MAPPING.put("Yakut", null);
        LANG_MAPPING.put("Yiddish", "ji");
        LANG_MAPPING.put("Zapotec", null);
        LANG_MAPPING.put("Zulu", "zu");
        LANG_MAPPING = Collections.unmodifiableMap(LANG_MAPPING);
    }
}
