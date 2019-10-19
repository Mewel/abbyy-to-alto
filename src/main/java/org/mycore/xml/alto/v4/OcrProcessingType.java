
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Information on how the text was created, including preprocessing, OCR processing, and postprocessing steps. Where possible, this draws from MIX's change history.
 * 
 * <p>Java class for ocrProcessingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ocrProcessingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preProcessingStep" type="{http://www.loc.gov/standards/alto/ns-v4#}processingStepType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ocrProcessingStep" type="{http://www.loc.gov/standards/alto/ns-v4#}processingStepType"/>
 *         &lt;element name="postProcessingStep" type="{http://www.loc.gov/standards/alto/ns-v4#}processingStepType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ocrProcessingType", propOrder = {
    "preProcessingStep",
    "ocrProcessingStep",
    "postProcessingStep"
})
@XmlSeeAlso({
    org.mycore.xml.alto.v4.DescriptionType.OCRProcessing.class
})
public class OcrProcessingType {

    protected List<ProcessingStepType> preProcessingStep;
    @XmlElement(required = true)
    protected ProcessingStepType ocrProcessingStep;
    protected List<ProcessingStepType> postProcessingStep;

    /**
     * Gets the value of the preProcessingStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preProcessingStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreProcessingStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessingStepType }
     * 
     * 
     */
    public List<ProcessingStepType> getPreProcessingStep() {
        if (preProcessingStep == null) {
            preProcessingStep = new ArrayList<ProcessingStepType>();
        }
        return this.preProcessingStep;
    }

    /**
     * Gets the value of the ocrProcessingStep property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingStepType }
     *     
     */
    public ProcessingStepType getOcrProcessingStep() {
        return ocrProcessingStep;
    }

    /**
     * Sets the value of the ocrProcessingStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingStepType }
     *     
     */
    public void setOcrProcessingStep(ProcessingStepType value) {
        this.ocrProcessingStep = value;
    }

    /**
     * Gets the value of the postProcessingStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postProcessingStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostProcessingStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProcessingStepType }
     * 
     * 
     */
    public List<ProcessingStepType> getPostProcessingStep() {
        if (postProcessingStep == null) {
            postProcessingStep = new ArrayList<ProcessingStepType>();
        }
        return this.postProcessingStep;
    }

}
