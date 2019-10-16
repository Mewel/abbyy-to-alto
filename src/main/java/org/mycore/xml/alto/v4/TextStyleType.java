
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A text style defines font properties of text. 
 * 
 * <p>Java class for TextStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TextStyleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attGroup ref="{http://www.loc.gov/standards/alto/ns-v4#}formattingAttributeGroup"/>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextStyleType")
public class TextStyleType {

    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "FONTFAMILY")
    protected String fontfamily;
    @XmlAttribute(name = "FONTTYPE")
    protected FontTypeType fonttype;
    @XmlAttribute(name = "FONTWIDTH")
    protected FontWidthType fontwidth;
    @XmlAttribute(name = "FONTSIZE", required = true)
    protected float fontsize;
    @XmlAttribute(name = "FONTCOLOR")
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] fontcolor;
    @XmlAttribute(name = "FONTSTYLE")
    protected List<String> fontstyle;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the fontfamily property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFONTFAMILY() {
        return fontfamily;
    }

    /**
     * Sets the value of the fontfamily property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFONTFAMILY(String value) {
        this.fontfamily = value;
    }

    /**
     * Gets the value of the fonttype property.
     * 
     * @return
     *     possible object is
     *     {@link FontTypeType }
     *     
     */
    public FontTypeType getFONTTYPE() {
        return fonttype;
    }

    /**
     * Sets the value of the fonttype property.
     * 
     * @param value
     *     allowed object is
     *     {@link FontTypeType }
     *     
     */
    public void setFONTTYPE(FontTypeType value) {
        this.fonttype = value;
    }

    /**
     * Gets the value of the fontwidth property.
     * 
     * @return
     *     possible object is
     *     {@link FontWidthType }
     *     
     */
    public FontWidthType getFONTWIDTH() {
        return fontwidth;
    }

    /**
     * Sets the value of the fontwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link FontWidthType }
     *     
     */
    public void setFONTWIDTH(FontWidthType value) {
        this.fontwidth = value;
    }

    /**
     * Gets the value of the fontsize property.
     * 
     */
    public float getFONTSIZE() {
        return fontsize;
    }

    /**
     * Sets the value of the fontsize property.
     * 
     */
    public void setFONTSIZE(float value) {
        this.fontsize = value;
    }

    /**
     * Gets the value of the fontcolor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getFONTCOLOR() {
        return fontcolor;
    }

    /**
     * Sets the value of the fontcolor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFONTCOLOR(byte[] value) {
        this.fontcolor = value;
    }

    /**
     * Gets the value of the fontstyle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fontstyle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFONTSTYLE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFONTSTYLE() {
        if (fontstyle == null) {
            fontstyle = new ArrayList<String>();
        }
        return this.fontstyle;
    }

}
