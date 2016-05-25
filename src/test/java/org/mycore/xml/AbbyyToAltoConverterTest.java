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

        assertNotNull(alto);
        assertNotNull(alto.getStyles());
        assertNotNull(alto.getDescription());
        assertNotNull(alto.getLayout());
        assertEquals(1, alto.getLayout().getPage().size());
        PageSpaceType pageSpace = alto.getLayout().getPage().get(0).getPrintSpace();
        assertNotNull(pageSpace);
        assertNotEquals(0, pageSpace.getContent().size());
        System.out.println(JAXBUtil.marshalAltoToString(alto));
    }

}
