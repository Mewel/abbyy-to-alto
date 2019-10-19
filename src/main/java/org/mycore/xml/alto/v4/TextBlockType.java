
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
 * A block of text.
 * 
 * <p>Java class for TextBlockType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TextBlockType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}BlockType">
 *       &lt;sequence minOccurs="0">
 *         &lt;element name="TextLine" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;sequence>
 *                     &lt;element name="Shape" type="{http://www.loc.gov/standards/alto/ns-v4#}ShapeType" minOccurs="0"/>
 *                   &lt;/sequence>
 *                   &lt;sequence maxOccurs="unbounded">
 *                     &lt;element name="String" type="{http://www.loc.gov/standards/alto/ns-v4#}StringType"/>
 *                     &lt;element name="SP" type="{http://www.loc.gov/standards/alto/ns-v4#}SPType" minOccurs="0"/>
 *                   &lt;/sequence>
 *                   &lt;element name="HYP" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                           &lt;attribute name="CONTENT" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="ID" type="{http://www.loc.gov/standards/alto/ns-v4#}TextLineID" />
 *                 &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *                 &lt;attribute name="TAGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *                 &lt;attribute name="PROCESSINGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *                 &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="BASELINE" type="{http://www.w3.org/2001/XMLSchema}float" />
 *                 &lt;attribute name="LANG" type="{http://www.w3.org/2001/XMLSchema}language" />
 *                 &lt;attribute name="CS" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}language" />
 *       &lt;attribute name="LANG" type="{http://www.w3.org/2001/XMLSchema}language" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TextBlockType", propOrder = {
    "textLine"
})
public class TextBlockType
    extends BlockType
{

    @XmlElement(name = "TextLine")
    protected List<TextBlockType.TextLine> textLine;
    @XmlAttribute(name = "language")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String language;
    @XmlAttribute(name = "LANG")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String lang;

    /**
     * Gets the value of the textLine property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the textLine property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTextLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextBlockType.TextLine }
     * 
     * 
     */
    public List<TextBlockType.TextLine> getTextLine() {
        if (textLine == null) {
            textLine = new ArrayList<TextBlockType.TextLine>();
        }
        return this.textLine;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;sequence>
     *           &lt;element name="Shape" type="{http://www.loc.gov/standards/alto/ns-v4#}ShapeType" minOccurs="0"/>
     *         &lt;/sequence>
     *         &lt;sequence maxOccurs="unbounded">
     *           &lt;element name="String" type="{http://www.loc.gov/standards/alto/ns-v4#}StringType"/>
     *           &lt;element name="SP" type="{http://www.loc.gov/standards/alto/ns-v4#}SPType" minOccurs="0"/>
     *         &lt;/sequence>
     *         &lt;element name="HYP" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
     *                 &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
     *                 &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
     *                 &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
     *                 &lt;attribute name="CONTENT" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *       &lt;attribute name="ID" type="{http://www.loc.gov/standards/alto/ns-v4#}TextLineID" />
     *       &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
     *       &lt;attribute name="TAGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
     *       &lt;attribute name="PROCESSINGREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
     *       &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
     *       &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
     *       &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
     *       &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
     *       &lt;attribute name="BASELINE" type="{http://www.w3.org/2001/XMLSchema}float" />
     *       &lt;attribute name="LANG" type="{http://www.w3.org/2001/XMLSchema}language" />
     *       &lt;attribute name="CS" type="{http://www.w3.org/2001/XMLSchema}boolean" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "shape",
        "stringAndSP",
        "hyp"
    })
    public static class TextLine {

        @XmlElement(name = "Shape")
        protected ShapeType shape;
        @XmlElements({
            @XmlElement(name = "String", required = true, type = StringType.class),
            @XmlElement(name = "SP", required = true, type = SPType.class)
        })
        protected List<Object> stringAndSP;
        @XmlElement(name = "HYP")
        protected TextBlockType.TextLine.HYP hyp;
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
        @XmlAttribute(name = "BASELINE")
        protected Float baseline;
        @XmlAttribute(name = "LANG")
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlSchemaType(name = "language")
        protected String lang;
        @XmlAttribute(name = "CS")
        protected Boolean cs;

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
         * Gets the value of the stringAndSP property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the stringAndSP property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getStringAndSP().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link StringType }
         * {@link SPType }
         * 
         * 
         */
        public List<Object> getStringAndSP() {
            if (stringAndSP == null) {
                stringAndSP = new ArrayList<Object>();
            }
            return this.stringAndSP;
        }

        /**
         * Gets the value of the hyp property.
         * 
         * @return
         *     possible object is
         *     {@link TextBlockType.TextLine.HYP }
         *     
         */
        public TextBlockType.TextLine.HYP getHYP() {
            return hyp;
        }

        /**
         * Sets the value of the hyp property.
         * 
         * @param value
         *     allowed object is
         *     {@link TextBlockType.TextLine.HYP }
         *     
         */
        public void setHYP(TextBlockType.TextLine.HYP value) {
            this.hyp = value;
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
         * Gets the value of the baseline property.
         * 
         * @return
         *     possible object is
         *     {@link Float }
         *     
         */
        public Float getBASELINE() {
            return baseline;
        }

        /**
         * Sets the value of the baseline property.
         * 
         * @param value
         *     allowed object is
         *     {@link Float }
         *     
         */
        public void setBASELINE(Float value) {
            this.baseline = value;
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
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;attribute name="HEIGHT" type="{http://www.w3.org/2001/XMLSchema}float" />
         *       &lt;attribute name="WIDTH" type="{http://www.w3.org/2001/XMLSchema}float" />
         *       &lt;attribute name="HPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
         *       &lt;attribute name="VPOS" type="{http://www.w3.org/2001/XMLSchema}float" />
         *       &lt;attribute name="CONTENT" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class HYP {

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

        }

    }

}
