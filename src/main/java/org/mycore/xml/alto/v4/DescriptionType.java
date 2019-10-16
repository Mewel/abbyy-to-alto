
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
 * <p>Java class for DescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeasurementUnit" type="{http://www.loc.gov/standards/alto/ns-v4#}MeasurementUnitType"/>
 *         &lt;element name="sourceImageInformation" type="{http://www.loc.gov/standards/alto/ns-v4#}sourceImageInformationType" minOccurs="0"/>
 *         &lt;element name="OCRProcessing" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}ocrProcessingType">
 *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Processing" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}processingStepType">
 *                 &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DescriptionType", propOrder = {
    "measurementUnit",
    "sourceImageInformation",
    "ocrProcessing",
    "processing"
})
public class DescriptionType {

    @XmlElement(name = "MeasurementUnit", required = true)
    @XmlSchemaType(name = "string")
    protected MeasurementUnitType measurementUnit;
    protected SourceImageInformationType sourceImageInformation;
    @XmlElement(name = "OCRProcessing")
    protected List<DescriptionType.OCRProcessing> ocrProcessing;
    @XmlElement(name = "Processing")
    protected List<DescriptionType.Processing> processing;

    /**
     * Gets the value of the measurementUnit property.
     * 
     * @return
     *     possible object is
     *     {@link MeasurementUnitType }
     *     
     */
    public MeasurementUnitType getMeasurementUnit() {
        return measurementUnit;
    }

    /**
     * Sets the value of the measurementUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasurementUnitType }
     *     
     */
    public void setMeasurementUnit(MeasurementUnitType value) {
        this.measurementUnit = value;
    }

    /**
     * Gets the value of the sourceImageInformation property.
     * 
     * @return
     *     possible object is
     *     {@link SourceImageInformationType }
     *     
     */
    public SourceImageInformationType getSourceImageInformation() {
        return sourceImageInformation;
    }

    /**
     * Sets the value of the sourceImageInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SourceImageInformationType }
     *     
     */
    public void setSourceImageInformation(SourceImageInformationType value) {
        this.sourceImageInformation = value;
    }

    /**
     * Gets the value of the ocrProcessing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ocrProcessing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOCRProcessing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType.OCRProcessing }
     * 
     * 
     */
    public List<DescriptionType.OCRProcessing> getOCRProcessing() {
        if (ocrProcessing == null) {
            ocrProcessing = new ArrayList<DescriptionType.OCRProcessing>();
        }
        return this.ocrProcessing;
    }

    /**
     * Gets the value of the processing property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processing property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessing().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DescriptionType.Processing }
     * 
     * 
     */
    public List<DescriptionType.Processing> getProcessing() {
        if (processing == null) {
            processing = new ArrayList<DescriptionType.Processing>();
        }
        return this.processing;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}ocrProcessingType">
     *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class OCRProcessing
        extends OcrProcessingType
    {

        @XmlAttribute(name = "ID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

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

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.loc.gov/standards/alto/ns-v4#}processingStepType">
     *       &lt;attribute name="ID" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Processing
        extends ProcessingStepType
    {

        @XmlAttribute(name = "ID", required = true)
        @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
        @XmlID
        @XmlSchemaType(name = "ID")
        protected String id;

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

    }

}
