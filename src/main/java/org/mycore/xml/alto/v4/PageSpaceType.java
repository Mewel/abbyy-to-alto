
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * A region on a page
 * 
 * <p>Java class for PageSpaceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PageSpaceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Shape" type="{http://www.loc.gov/standards/alto/ns-v4#}ShapeType" minOccurs="0"/>
 *         &lt;sequence maxOccurs="unbounded" minOccurs="0">
 *           &lt;group ref="{http://www.loc.gov/standards/alto/ns-v4#}BlockGroup"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceTypeID" />
 *       &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="PROCESSINGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
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
@XmlType(name = "PageSpaceType", propOrder = {
    "shape",
    "textBlockOrIllustrationOrGraphicalElement"
})
public class PageSpaceType {

    @XmlElement(name = "Shape")
    protected ShapeType shape;
    @XmlElements({
        @XmlElement(name = "TextBlock", type = TextBlockType.class),
        @XmlElement(name = "Illustration", type = IllustrationType.class),
        @XmlElement(name = "GraphicalElement", type = GraphicalElementType.class),
        @XmlElement(name = "ComposedBlock", type = ComposedBlockType.class)
    })
    protected List<BlockType> textBlockOrIllustrationOrGraphicalElement;
    @XmlAttribute(name = "ID")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "STYLEREFS")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> stylerefs;
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
     * Gets the value of the textBlockOrIllustrationOrGraphicalElement property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textBlockOrIllustrationOrGraphicalElement property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextBlockOrIllustrationOrGraphicalElement().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextBlockType }
     * {@link IllustrationType }
     * {@link GraphicalElementType }
     * {@link ComposedBlockType }
     * 
     * 
     */
    public List<BlockType> getTextBlockOrIllustrationOrGraphicalElement() {
        if (textBlockOrIllustrationOrGraphicalElement == null) {
            textBlockOrIllustrationOrGraphicalElement = new ArrayList<BlockType>();
        }
        return this.textBlockOrIllustrationOrGraphicalElement;
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

}
