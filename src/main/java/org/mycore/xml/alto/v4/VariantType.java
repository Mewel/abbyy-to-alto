
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Alternative (combined) character for the glyph, outlined by OCR engine or similar recognition processes.
 * 				In case the variant are two (combining) characters, two characters are outlined in one Variant element.
 * 				E.g. a Glyph element with CONTENT="m" can have a Variant element with the content "rn".
 * 				Details for different use-cases see on the samples on GitHub.
 * 			
 * 
 * <p>Java class for VariantType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VariantType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="CONTENT">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;maxLength value="3"/>
 *             &lt;whiteSpace value="preserve"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="VC">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VariantType")
public class VariantType {

    @XmlAttribute(name = "CONTENT")
    protected String content;
    @XmlAttribute(name = "VC")
    protected Float vc;

    /**
     * Gets the value of the content property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTENT() {
        return content;
    }

    /**
     * Sets the value of the content property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTENT(String value) {
        this.content = value;
    }

    /**
     * Gets the value of the vc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getVC() {
        return vc;
    }

    /**
     * Sets the value of the vc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setVC(Float value) {
        this.vc = value;
    }

}
