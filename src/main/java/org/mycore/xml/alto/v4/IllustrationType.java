
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * A picture or image.
 * 
 * <p>Java class for IllustrationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IllustrationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}BlockType">
 *       &lt;attribute name="TYPE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FILEID" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IllustrationType")
public class IllustrationType
    extends BlockType
{

    @XmlAttribute(name = "TYPE")
    protected String type;
    @XmlAttribute(name = "FILEID")
    protected String fileid;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the fileid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFILEID() {
        return fileid;
    }

    /**
     * Sets the value of the fileid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFILEID(String value) {
        this.fileid = value;
    }

}
