
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The attribute ROTATION tells the rotation of the e.g. text or 
 * 				           illustration within the block. The value is in degrees counterclockwise. 
 * 
 * <p>Java class for EllipseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EllipseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="HPOS" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="VPOS" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="HLENGTH" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="VLENGTH" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="ROTATION" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EllipseType")
public class EllipseType {

    @XmlAttribute(name = "HPOS", required = true)
    protected float hpos;
    @XmlAttribute(name = "VPOS", required = true)
    protected float vpos;
    @XmlAttribute(name = "HLENGTH", required = true)
    protected float hlength;
    @XmlAttribute(name = "VLENGTH", required = true)
    protected float vlength;
    @XmlAttribute(name = "ROTATION")
    protected Float rotation;

    /**
     * Gets the value of the hpos property.
     * 
     */
    public float getHPOS() {
        return hpos;
    }

    /**
     * Sets the value of the hpos property.
     * 
     */
    public void setHPOS(float value) {
        this.hpos = value;
    }

    /**
     * Gets the value of the vpos property.
     * 
     */
    public float getVPOS() {
        return vpos;
    }

    /**
     * Sets the value of the vpos property.
     * 
     */
    public void setVPOS(float value) {
        this.vpos = value;
    }

    /**
     * Gets the value of the hlength property.
     * 
     */
    public float getHLENGTH() {
        return hlength;
    }

    /**
     * Sets the value of the hlength property.
     * 
     */
    public void setHLENGTH(float value) {
        this.hlength = value;
    }

    /**
     * Gets the value of the vlength property.
     * 
     */
    public float getVLENGTH() {
        return vlength;
    }

    /**
     * Sets the value of the vlength property.
     * 
     */
    public void setVLENGTH(float value) {
        this.vlength = value;
    }

    /**
     * Gets the value of the rotation property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getROTATION() {
        return rotation;
    }

    /**
     * Sets the value of the rotation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setROTATION(Float value) {
        this.rotation = value;
    }

}
