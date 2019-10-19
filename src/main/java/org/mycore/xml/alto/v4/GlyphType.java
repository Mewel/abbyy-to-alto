
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * 
 * 				Modern OCR software stores information on glyph level. A glyph is essentially a character or ligature.
 * 				Accordingly the value for the glyph element will be defined as follows:
 * 				Pre-composed representation = base + combining character(s) (decomposed representation)
 * 				See http://www.fileformat.info/info/unicode/char/0101/index.htm
 * 				"U+0101" = (U+0061) + (U+0304)
 * 				"combining characters" ("base characters" in combination with non-spacing marks or characters which are combined to one) are represented as one "glyph", e.g. áàâ.
 * 				
 * 				Each glyph has its own coordinate information and must be separately addressable as a distinct object.
 * 				Correction and verification processes can be carried out for individual characters.
 * 				
 * 				Post-OCR analysis of the text as well as adaptive OCR algorithm must be able to record information on glyph level.
 * 				In order to reproduce the decision of the OCR software, optional characters must be recorded. These are called variants.
 * 				The OCR software evaluates each variant and picks the one with the highest confidence score as the glyph.
 * 				The confidence score expresses how confident the OCR software is that a single glyph had been recognized correctly.
 * 				
 * 				The glyph elements are in order of the word. Each glyph need to be recorded to built up the whole word sequence.
 * 				
 * 				The glyph’s CONTENT attribute is no replacement for the string’s CONTENT attribute.
 * 				Due to post-processing steps such as correction the values of both attributes may be inconsistent. 
 * 			
 * 
 * <p>Java class for GlyphType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GlyphType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="Shape" type="{http://www.loc.gov/standards/alto/ns-v4#}ShapeType" minOccurs="0"/>
 *         &lt;element name="Variant" type="{http://www.loc.gov/standards/alto/ns-v4#}VariantType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="CONTENT" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;length value="1"/>
 *             &lt;whiteSpace value="preserve"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="GC">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}float">
 *             &lt;minInclusive value="0"/>
 *             &lt;maxInclusive value="1"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GlyphType", propOrder = {
    "shape",
    "variant"
})
public class GlyphType {

    @XmlElement(name = "Shape")
    protected ShapeType shape;
    @XmlElement(name = "Variant")
    protected List<VariantType> variant;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "CONTENT", required = true)
    protected String content;
    @XmlAttribute(name = "GC")
    protected Float gc;
    @XmlAttribute(name = "HEIGHT")
    protected Float height;
    @XmlAttribute(name = "WIDTH")
    protected Float width;
    @XmlAttribute(name = "HPOS")
    protected Float hpos;
    @XmlAttribute(name = "VPOS")
    protected Float vpos;

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
     * Gets the value of the variant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the variant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVariant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VariantType }
     * 
     * 
     */
    public List<VariantType> getVariant() {
        if (variant == null) {
            variant = new ArrayList<VariantType>();
        }
        return this.variant;
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
     * Gets the value of the gc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getGC() {
        return gc;
    }

    /**
     * Sets the value of the gc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setGC(Float value) {
        this.gc = value;
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

}
