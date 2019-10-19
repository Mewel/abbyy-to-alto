package org.mycore.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.alto.v4.*;

import java.io.InputStream;

import static org.junit.Assert.*;

public class AbbyyToAltoV4ConverterTest {

    private static Logger LOGGER = LogManager.getLogger(AbbyyToAltoV4ConverterTest.class);

    /**
     * Use the abbyy.xml example document
     * 
     * @throws Exception something went wrong
     */
    @Test
    public void convert() throws Exception {
        try (InputStream inputStream = AbbyyToAltoConverter.class.getResourceAsStream("/abbyy.xml")) {
            Document document = JAXBUtil.unmarshalAbbyyDocument(inputStream);
            AltoType alto = new AbbyyToAltoV4Converter().convert(document);
            assertNotNull("alto object should not be null", alto);
            assertNotNull("alto Styles element should not be null", alto.getStyles());
            assertEquals("there should be '14' TextStyle elements", 14, alto.getStyles().getTextStyle().size());
            assertEquals("there should be '30' ParagraphStyle elements", 30,
                alto.getStyles().getParagraphStyle().size());
            assertNotNull("alto description should not be null", alto.getDescription());
            assertNotNull("alto layout should not be null", alto.getLayout());
            assertEquals("there should be exactly one page", 1, alto.getLayout().getPage().size());
            PageSpaceType pageSpace = alto.getLayout().getPage().get(0).getPrintSpace();
            assertNotNull("PrintSpace should not be null", pageSpace);
            assertNotEquals("PrintSpace should have content", 0, pageSpace.getTextBlockOrIllustrationOrGraphicalElement().size());
            BlockType block1 = pageSpace.getTextBlockOrIllustrationOrGraphicalElement().get(0);
            assertEquals("First block should be of type composed block", ComposedBlockType.class, block1.getClass());
            ComposedBlockType composedBlock = (ComposedBlockType) block1;
            assertNotEquals("Composed block should have content", 0, composedBlock.getTextBlockOrIllustrationOrGraphicalElement().size());
            BlockType block2 = composedBlock.getTextBlockOrIllustrationOrGraphicalElement().get(0);
            assertEquals("Second block should be of type text", TextBlockType.class, block2.getClass());
            TextBlockType textBlock = (TextBlockType) block2;
            assertNotEquals("Text block should have lines", 0, textBlock.getTextLine().size());
            TextBlockType.TextLine textLine = textBlock.getTextLine().get(0);
            assertEquals("Text line should have language set", "de", textLine.getLANG());
            assertNotEquals("Text line should have contents", 0, textLine.getStringAndSP().size());
            Object stringOrSp = textLine.getStringAndSP().get(0);
            assertEquals("First line item should be of type string", StringType.class, stringOrSp.getClass());
            StringType string = (StringType) stringOrSp;
            assertNotEquals("String should have glyphs", 0, string.getGlyph().size());
            GlyphType glyph = string.getGlyph().get(0);
            assertEquals("First glyph should be '/'", "/", glyph.getCONTENT());
            //LOGGER.info(JAXBUtil.marshalAltoV4ToString(alto));
        }
    }

    /**
     * Use oldGerman.xml example document
     * 
     * @throws Exception something went wrong
     */
    @Test
    public void convertOldGerman() throws Exception {
        // convert
        try (InputStream inputStream = AbbyyToAltoConverter.class.getResourceAsStream("/oldGerman.xml")) {
            Document document = JAXBUtil.unmarshalAbbyyDocument(inputStream);
            AbbyyToAltoV4Converter converter = new AbbyyToAltoV4Converter();
            converter.setDefaultFontFamily("Times");
            converter.setDefaultFontSize(10f);
            converter.setEnableConfidence(false);
            AltoType alto = converter.convert(document);
            assertNotNull("alto object should not be null", alto);
            assertEquals("there should be 1 TextStyle element", 1, alto.getStyles().getTextStyle().size());
            assertEquals("there should be exactly one page", 1, alto.getLayout().getPage().size());
            //LOGGER.info(JAXBUtil.marshalAltoV4ToString(alto));
        }
    }

}
