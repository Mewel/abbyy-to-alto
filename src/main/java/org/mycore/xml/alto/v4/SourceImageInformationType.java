
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * Information to identify the image file from which the OCR text was created.
 * 
 * <p>Java class for sourceImageInformationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sourceImageInformationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileName" type="{http://www.loc.gov/standards/alto/ns-v4#}fileNameType" minOccurs="0"/>
 *         &lt;element name="fileIdentifier" type="{http://www.loc.gov/standards/alto/ns-v4#}fileIdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentIdentifier" type="{http://www.loc.gov/standards/alto/ns-v4#}documentIdentifierType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sourceImageInformationType", propOrder = {
    "fileName",
    "fileIdentifier",
    "documentIdentifier"
})
public class SourceImageInformationType {

    protected String fileName;
    protected List<FileIdentifierType> fileIdentifier;
    protected List<DocumentIdentifierType> documentIdentifier;

    /**
     * Gets the value of the fileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the value of the fileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * Gets the value of the fileIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fileIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFileIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FileIdentifierType }
     * 
     * 
     */
    public List<FileIdentifierType> getFileIdentifier() {
        if (fileIdentifier == null) {
            fileIdentifier = new ArrayList<FileIdentifierType>();
        }
        return this.fileIdentifier;
    }

    /**
     * Gets the value of the documentIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentIdentifierType }
     * 
     * 
     */
    public List<DocumentIdentifierType> getDocumentIdentifier() {
        if (documentIdentifier == null) {
            documentIdentifier = new ArrayList<DocumentIdentifierType>();
        }
        return this.documentIdentifier;
    }

}
