
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
 * One page of a book or journal.
 * 
 * <p>Java class for PageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TopMargin" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceType" minOccurs="0"/>
 *         &lt;element name="LeftMargin" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceType" minOccurs="0"/>
 *         &lt;element name="RightMargin" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceType" minOccurs="0"/>
 *         &lt;element name="BottomMargin" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceType" minOccurs="0"/>
 *         &lt;element name="PrintSpace" type="{http://www.loc.gov/standards/alto/ns-v4#}PageSpaceType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ID" use="required" type="{http://www.loc.gov/standards/alto/ns-v4#}PageID" />
 *       &lt;attribute name="PAGECLASS" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="PROCESSINGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *       &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="PHYSICAL_IMG_NR" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="PRINTED_IMG_NR" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="QUALITY" type="{http://www.loc.gov/standards/alto/ns-v4#}QualityType" />
 *       &lt;attribute name="QUALITY_DETAIL" type="{http://www.loc.gov/standards/alto/ns-v4#}QualityDetailType" />
 *       &lt;attribute name="POSITION" type="{http://www.loc.gov/standards/alto/ns-v4#}PositionType" />
 *       &lt;attribute name="PROCESSING" type="{http://www.w3.org/2001/XMLSchema}IDREF" />
 *       &lt;attribute name="ACCURACY" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="PC" type="{http://www.loc.gov/standards/alto/ns-v4#}PCType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PageType", propOrder = {
    "topMargin",
    "leftMargin",
    "rightMargin",
    "bottomMargin",
    "printSpace"
})
public class PageType {

    @XmlElement(name = "TopMargin")
    protected PageSpaceType topMargin;
    @XmlElement(name = "LeftMargin")
    protected PageSpaceType leftMargin;
    @XmlElement(name = "RightMargin")
    protected PageSpaceType rightMargin;
    @XmlElement(name = "BottomMargin")
    protected PageSpaceType bottomMargin;
    @XmlElement(name = "PrintSpace")
    protected PageSpaceType printSpace;
    @XmlAttribute(name = "ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "PAGECLASS")
    protected String pageclass;
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
    @XmlAttribute(name = "PHYSICAL_IMG_NR", required = true)
    protected float physicalimgnr;
    @XmlAttribute(name = "PRINTED_IMG_NR")
    protected String printedimgnr;
    @XmlAttribute(name = "QUALITY")
    protected QualityType quality;
    @XmlAttribute(name = "QUALITY_DETAIL")
    protected String qualitydetail;
    @XmlAttribute(name = "POSITION")
    protected PositionType position;
    @XmlAttribute(name = "PROCESSING")
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Object processing;
    @XmlAttribute(name = "ACCURACY")
    protected Float accuracy;
    @XmlAttribute(name = "PC")
    protected Float pc;

    /**
     * Gets the value of the topMargin property.
     * 
     * @return
     *     possible object is
     *     {@link PageSpaceType }
     *     
     */
    public PageSpaceType getTopMargin() {
        return topMargin;
    }

    /**
     * Sets the value of the topMargin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageSpaceType }
     *     
     */
    public void setTopMargin(PageSpaceType value) {
        this.topMargin = value;
    }

    /**
     * Gets the value of the leftMargin property.
     * 
     * @return
     *     possible object is
     *     {@link PageSpaceType }
     *     
     */
    public PageSpaceType getLeftMargin() {
        return leftMargin;
    }

    /**
     * Sets the value of the leftMargin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageSpaceType }
     *     
     */
    public void setLeftMargin(PageSpaceType value) {
        this.leftMargin = value;
    }

    /**
     * Gets the value of the rightMargin property.
     * 
     * @return
     *     possible object is
     *     {@link PageSpaceType }
     *     
     */
    public PageSpaceType getRightMargin() {
        return rightMargin;
    }

    /**
     * Sets the value of the rightMargin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageSpaceType }
     *     
     */
    public void setRightMargin(PageSpaceType value) {
        this.rightMargin = value;
    }

    /**
     * Gets the value of the bottomMargin property.
     * 
     * @return
     *     possible object is
     *     {@link PageSpaceType }
     *     
     */
    public PageSpaceType getBottomMargin() {
        return bottomMargin;
    }

    /**
     * Sets the value of the bottomMargin property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageSpaceType }
     *     
     */
    public void setBottomMargin(PageSpaceType value) {
        this.bottomMargin = value;
    }

    /**
     * Gets the value of the printSpace property.
     * 
     * @return
     *     possible object is
     *     {@link PageSpaceType }
     *     
     */
    public PageSpaceType getPrintSpace() {
        return printSpace;
    }

    /**
     * Sets the value of the printSpace property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageSpaceType }
     *     
     */
    public void setPrintSpace(PageSpaceType value) {
        this.printSpace = value;
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
     * Gets the value of the pageclass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPAGECLASS() {
        return pageclass;
    }

    /**
     * Sets the value of the pageclass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPAGECLASS(String value) {
        this.pageclass = value;
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
     * Gets the value of the physicalimgnr property.
     * 
     */
    public float getPHYSICALIMGNR() {
        return physicalimgnr;
    }

    /**
     * Sets the value of the physicalimgnr property.
     * 
     */
    public void setPHYSICALIMGNR(float value) {
        this.physicalimgnr = value;
    }

    /**
     * Gets the value of the printedimgnr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPRINTEDIMGNR() {
        return printedimgnr;
    }

    /**
     * Sets the value of the printedimgnr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPRINTEDIMGNR(String value) {
        this.printedimgnr = value;
    }

    /**
     * Gets the value of the quality property.
     * 
     * @return
     *     possible object is
     *     {@link QualityType }
     *     
     */
    public QualityType getQUALITY() {
        return quality;
    }

    /**
     * Sets the value of the quality property.
     * 
     * @param value
     *     allowed object is
     *     {@link QualityType }
     *     
     */
    public void setQUALITY(QualityType value) {
        this.quality = value;
    }

    /**
     * Gets the value of the qualitydetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQUALITYDETAIL() {
        return qualitydetail;
    }

    /**
     * Sets the value of the qualitydetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQUALITYDETAIL(String value) {
        this.qualitydetail = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getPOSITION() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setPOSITION(PositionType value) {
        this.position = value;
    }

    /**
     * Gets the value of the processing property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPROCESSING() {
        return processing;
    }

    /**
     * Sets the value of the processing property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPROCESSING(Object value) {
        this.processing = value;
    }

    /**
     * Gets the value of the accuracy property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getACCURACY() {
        return accuracy;
    }

    /**
     * Sets the value of the accuracy property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setACCURACY(Float value) {
        this.accuracy = value;
    }

    /**
     * Gets the value of the pc property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getPC() {
        return pc;
    }

    /**
     * Sets the value of the pc property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setPC(Float value) {
        this.pc = value;
    }

}
