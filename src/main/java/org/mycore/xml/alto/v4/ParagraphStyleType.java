
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A paragraph style defines formatting properties of text blocks.
 * 
 * <p>Java class for ParagraphStyleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ParagraphStyleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="ID" use="required" type="{http://www.loc.gov/standards/alto/ns-v4#}ParagraphStyleID" />
 *       &lt;attribute name="ALIGN">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="Left"/>
 *             &lt;enumeration value="Right"/>
 *             &lt;enumeration value="Center"/>
 *             &lt;enumeration value="Block"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *       &lt;attribute name="LEFT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="RIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="LINESPACE" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="FIRSTLINE" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ParagraphStyleType")
public class ParagraphStyleType {

    @XmlAttribute(name = "ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "ALIGN")
    protected String align;
    @XmlAttribute(name = "LEFT")
    protected Float left;
    @XmlAttribute(name = "RIGHT")
    protected Float right;
    @XmlAttribute(name = "LINESPACE")
    protected Float linespace;
    @XmlAttribute(name = "FIRSTLINE")
    protected Float firstline;

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
     * Gets the value of the align property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getALIGN() {
        return align;
    }

    /**
     * Sets the value of the align property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setALIGN(String value) {
        this.align = value;
    }

    /**
     * Gets the value of the left property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLEFT() {
        return left;
    }

    /**
     * Sets the value of the left property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLEFT(Float value) {
        this.left = value;
    }

    /**
     * Gets the value of the right property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getRIGHT() {
        return right;
    }

    /**
     * Sets the value of the right property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setRIGHT(Float value) {
        this.right = value;
    }

    /**
     * Gets the value of the linespace property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getLINESPACE() {
        return linespace;
    }

    /**
     * Sets the value of the linespace property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setLINESPACE(Float value) {
        this.linespace = value;
    }

    /**
     * Gets the value of the firstline property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getFIRSTLINE() {
        return firstline;
    }

    /**
     * Sets the value of the firstline property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setFIRSTLINE(Float value) {
        this.firstline = value;
    }

}
