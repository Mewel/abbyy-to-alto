
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * A circle shape. HPOS and VPOS describe the center of the circle.
 * 
 * <p>Java class for CircleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CircleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="HPOS" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="VPOS" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="RADIUS" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CircleType")
public class CircleType {

    @XmlAttribute(name = "HPOS", required = true)
    protected float hpos;
    @XmlAttribute(name = "VPOS", required = true)
    protected float vpos;
    @XmlAttribute(name = "RADIUS", required = true)
    protected float radius;

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
     * Gets the value of the radius property.
     * 
     */
    public float getRADIUS() {
        return radius;
    }

    /**
     * Sets the value of the radius property.
     * 
     */
    public void setRADIUS(float value) {
        this.radius = value;
    }

}
