package org.mycore.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.alto.v2.Alto;
import org.mycore.xml.alto.v2.PageSpaceType;

public class AbbyyToAltoConverterTest {

    @Test
    public void convert() throws Exception {
        InputStream inputStream = AbbyyToAltoConverter.class.getResourceAsStream("/abbyy.xml");
        Document document = JAXBUtil.unmarshalAbbyyDocument(inputStream);
        Alto alto = new AbbyyToAltoConverter().convert(document);
        assertNotNull("alto object should not be null", alto);
        assertNotNull("alto Styles element should not be null", alto.getStyles());
        assertEquals("there should be '14' TextStyle elements", 14, alto.getStyles().getTextStyle().size());
        assertEquals("there should be '30' ParagraphStyle elements", 30, alto.getStyles().getParagraphStyle().size());
        assertNotNull("alto description should not be null", alto.getDescription());
        assertNotNull("alto layout should not be null", alto.getLayout());
        assertEquals("there should be exactly one page", 1, alto.getLayout().getPage().size());
        PageSpaceType pageSpace = alto.getLayout().getPage().get(0).getPrintSpace();
        assertNotNull("PrintSpace should not be null", pageSpace);
        assertNotEquals("PrintSpace should have content", 0, pageSpace.getContent().size());
        System.out.println(JAXBUtil.marshalAltoToString(alto));
    }

    @Test
    public void convertOldGerman()   throws Exception {
        InputStream inputStream = AbbyyToAltoConverter.class.getResourceAsStream("/OldGerman.xml");
        Document document = JAXBUtil.unmarshalAbbyyDocument(inputStream);
        Alto alto = new AbbyyToAltoConverter().convert(document);
        assertNotNull("alto object should not be null", alto);
        assertEquals("there should be 1 TextStyle element", 1, alto.getStyles().getTextStyle().size());
        assertEquals("there should be exactly one page", 1, alto.getLayout().getPage().size());
    }

}
