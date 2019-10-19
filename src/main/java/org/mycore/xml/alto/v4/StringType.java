
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A sequence of chars. Strings are separated by white spaces or hyphenation chars.
 * 
 * <p>Java class for StringType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StringType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Shape" type="{http://www.loc.gov/standards/alto/ns-v4#}ShapeType" minOccurs="0"/>
 *         &lt;element name="ALTERNATIVE" type="{http://www.loc.gov/standards/alto/ns-v4#}ALTERNATIVEType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Glyph" type="{http://www.loc.gov/standards/alto/ns-v4#}GlyphType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.loc.gov/standards/alto/ns-v4#}StringTypeID" />
 *       &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="TAGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="PROCESSINGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="CONTENT" use="required" type="{http://www.loc.gov/standards/alto/ns-v4#}CONTENTType" />
 *       &lt;attribute name="STYLE" type="{http://www.loc.gov/standards/alto/ns-v4#}fontStylesType" />
 *       &lt;attribute name="SUBS_TYPE" type="{http://www.loc.gov/standards/alto/ns-v4#}SUBS_TYPEType" />
 *       &lt;attribute name="SUBS_CONTENT" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="WC" type="{http://www.loc.gov/standards/alto/ns-v4#}WCType" />
 *       &lt;attribute name="CC" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CS" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="LANG" type="{http://www.w3.org/2001/XMLSchema}language" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StringType", propOrder = {
    "shape",
    "alternative",
    "glyph"
})
public class StringType {

    @XmlElement(name = "Shape")
    protected ShapeType shape;
    @XmlElement(name = "ALTERNATIVE")
    protected List<ALTERNATIVEType> alternative;
    @XmlElement(name = "Glyph")
    protected List<GlyphType> glyph;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "STYLEREFS")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> stylerefs;
    @XmlAttribute(name = "TAGREFS")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> tagrefs;
    @XmlAttribute(name = "PROCESSINGREFS")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> processingrefs;
    @XmlAttribute(name = "HEIGHT")
    protected Float height;
    @XmlAttribute(name = "WIDTH")
    protected Float width;
    @XmlAttribute(name = "HPOS")
    protected Float hpos;
    @XmlAttribute(name = "VPOS")
    protected Float vpos;
    @XmlAttribute(name = "CONTENT", required = true)
    protected String content;
    @XmlAttribute(name = "STYLE")
    protected List<String> style;
    @XmlAttribute(name = "SUBS_TYPE")
    protected SUBSTYPEType substype;
    @XmlAttribute(name = "SUBS_CONTENT")
    protected String subscontent;
    @XmlAttribute(name = "WC")
    protected Float wc;
    @XmlAttribute(name = "CC")
    protected String cc;
    @XmlAttribute(name = "CS")
    protected Boolean cs;
    @XmlAttribute(name = "LANG")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Gets the value of the shape property.
     * 
     * @return
     *     possible object is
     *     {@link ShapeType }
     *     
     */
    public ShapeType getShape() {
        return shape;
    }

    /**
     * Sets the value of the shape property.
     * 
     * @param value
     *     allowed object is
     *     {@link ShapeType }
     *     
     */
    public void setShape(ShapeType value) {
        this.shape = value;
    }

    /**
     * Gets the value of the alternative property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternative property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getALTERNATIVE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ALTERNATIVEType }
     * 
     * 
     */
    public List<ALTERNATIVEType> getALTERNATIVE() {
        if (alternative == null) {
            alternative = new ArrayList<ALTERNATIVEType>();
        }
        return this.alternative;
    }

    /**
     * Gets the value of the glyph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the glyph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGlyph().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GlyphType }
     * 
     * 
     */
    public List<GlyphType> getGlyph() {
        if (glyph == null) {
            glyph = new ArrayList<GlyphType>();
        }
        return this.glyph;
    }

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
     * Gets the value of the stylerefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stylerefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTYLEREFS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getSTYLEREFS() {
        if (stylerefs == null) {
            stylerefs = new ArrayList<Object>();
        }
        return this.stylerefs;
    }

    /**
     * Gets the value of the tagrefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tagrefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTAGREFS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getTAGREFS() {
        if (tagrefs == null) {
            tagrefs = new ArrayList<Object>();
        }
        return this.tagrefs;
    }

    /**
     * Gets the value of the processingrefs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processingrefs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPROCESSINGREFS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getPROCESSINGREFS() {
        if (processingrefs == null) {
            processingrefs = new ArrayList<Object>();
        }
        return this.processingrefs;
    }

    /**
     * Gets the value of the height property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getHEIGHT() {
        return height;
    }

    /**
     * Sets the value of the height property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setHEIGHT(Float value) {
        this.height = value;
    }

    /**
     * Gets the value of the width property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWIDTH() {
        return width;
    }

    /**
     * Sets the value of the width property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWIDTH(Float value) {
        this.width = value;
    }

    /**
     * Gets the value of the hpos property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getHPOS() {
        return hpos;
    }

    /**
     * Sets the value of the hpos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setHPOS(Float value) {
        this.hpos = value;
    }

    /**
     * Gets the value of the vpos property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getVPOS() {
        return vpos;
    }

    /**
     * Sets the value of the vpos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setVPOS(Float value) {
        this.vpos = value;
    }

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
     * Gets the value of the style property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the style property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSTYLE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSTYLE() {
        if (style == null) {
            style = new ArrayList<String>();
        }
        return this.style;
    }

    /**
     * Gets the value of the substype property.
     * 
     * @return
     *     possible object is
     *     {@link SUBSTYPEType }
     *     
     */
    public SUBSTYPEType getSUBSTYPE() {
        return substype;
    }

    /**
     * Sets the value of the substype property.
     * 
     * @param value
     *     allowed object is
     *     {@link SUBSTYPEType }
     *     
     */
    public void setSUBSTYPE(SUBSTYPEType value) {
        this.substype = value;
    }

    /**
     * Gets the value of the subscontent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSUBSCONTENT() {
        return subscontent;
    }

    /**
     * Sets the value of the subscontent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSUBSCONTENT(String value) {
        this.subscontent = value;
    }

    /**
     * Gets the value of the wc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getWC() {
        return wc;
    }

    /**
     * Sets the value of the wc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setWC(Float value) {
        this.wc = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCC() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCC(String value) {
        this.cc = value;
    }

    /**
     * Gets the value of the cs property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCS() {
        return cs;
    }

    /**
     * Sets the value of the cs property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCS(Boolean value) {
        this.cs = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLANG() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLANG(String value) {
        this.lang = value;
    }

}
