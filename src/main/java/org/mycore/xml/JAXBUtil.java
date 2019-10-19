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
import org.mycore.xml.abbyy.v10.Document;
import org.mycore.xml.alto.v2.Alto;
import org.mycore.xml.alto.v4.AltoType;
import org.mycore.xml.alto.v4.ObjectFactory;

import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Util class for jaxb marshalling.
 *
 * @author Matthias Eichner
 */
public abstract class JAXBUtil {

    private static Logger LOGGER = LogManager.getLogger();

    private static Unmarshaller ABBYY_UNMARSHALLER;

    private static Marshaller ALTO_V2_MARSHALLER;
    private static Marshaller ALTO_V4_MARSHALLER;

    static {
        try {
            JAXBContext abbyyContext = JAXBContext.newInstance(Document.class);
            ABBYY_UNMARSHALLER = abbyyContext.createUnmarshaller();

            JAXBContext altoV2Context = JAXBContext.newInstance(Alto.class);
            ALTO_V2_MARSHALLER = altoV2Context.createMarshaller();
            ALTO_V2_MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ALTO_V2_MARSHALLER.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            JAXBContext altoV4Context = JAXBContext.newInstance(AltoType.class);
            ALTO_V4_MARSHALLER = altoV4Context.createMarshaller();
            ALTO_V4_MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ALTO_V4_MARSHALLER.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        } catch (Exception exc) {
            LOGGER.error("error while initializing jaxb", exc);
        }
    }

    public static synchronized Document unmarshalAbbyyDocument(InputStream inputStream) throws JAXBException {
        JAXBElement<Document> jaxbDocument = ABBYY_UNMARSHALLER.unmarshal(new StreamSource(inputStream),
                Document.class);
        return jaxbDocument.getValue();
    }

    public static synchronized void marshalAlto(Alto alto, OutputStream out) throws JAXBException {
        ALTO_V2_MARSHALLER.marshal(alto, out);
    }

    public static synchronized String marshalAltoToString(Alto alto) throws JAXBException {
        StringWriter writer = new StringWriter();
        ALTO_V2_MARSHALLER.marshal(alto, writer);
        return writer.toString();
    }

    public static synchronized void marshalAltoV4(AltoType alto, OutputStream out) throws JAXBException {
        ALTO_V4_MARSHALLER.marshal(new ObjectFactory().createAlto(alto), out);
    }

    public static synchronized String marshalAltoV4ToString(AltoType alto) throws JAXBException {
        StringWriter writer = new StringWriter();
        ALTO_V4_MARSHALLER.marshal(new ObjectFactory().createAlto(alto), writer);
        return writer.toString();
    }

}
