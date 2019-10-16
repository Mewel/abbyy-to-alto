
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Description of the processing step.
 * 
 * <p>Java class for processingStepType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processingStepType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="processingCategory" type="{http://www.loc.gov/standards/alto/ns-v4#}processingCategoryType" minOccurs="0"/>
 *         &lt;element name="processingDateTime" type="{http://www.loc.gov/standards/alto/ns-v4#}dateTimeType" minOccurs="0"/>
 *         &lt;element name="processingAgency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processingStepDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="processingStepSettings" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processingSoftware" type="{http://www.loc.gov/standards/alto/ns-v4#}processingSoftwareType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processingStepType", propOrder = {
    "processingCategory",
    "processingDateTime",
    "processingAgency",
    "processingStepDescription",
    "processingStepSettings",
    "processingSoftware"
})
@XmlSeeAlso({
    org.mycore.xml.alto.v4.DescriptionType.Processing.class
})
public class ProcessingStepType {

    @XmlList
    protected List<String> processingCategory;
    protected String processingDateTime;
    protected String processingAgency;
    protected List<String> processingStepDescription;
    protected String processingStepSettings;
    protected ProcessingSoftwareType processingSoftware;

    /**
     * Gets the value of the processingCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processingCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessingCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProcessingCategory() {
        if (processingCategory == null) {
            processingCategory = new ArrayList<String>();
        }
        return this.processingCategory;
    }

    /**
     * Gets the value of the processingDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingDateTime() {
        return processingDateTime;
    }

    /**
     * Sets the value of the processingDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingDateTime(String value) {
        this.processingDateTime = value;
    }

    /**
     * Gets the value of the processingAgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingAgency() {
        return processingAgency;
    }

    /**
     * Sets the value of the processingAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingAgency(String value) {
        this.processingAgency = value;
    }

    /**
     * Gets the value of the processingStepDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processingStepDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessingStepDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProcessingStepDescription() {
        if (processingStepDescription == null) {
            processingStepDescription = new ArrayList<String>();
        }
        return this.processingStepDescription;
    }

    /**
     * Gets the value of the processingStepSettings property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessingStepSettings() {
        return processingStepSettings;
    }

    /**
     * Sets the value of the processingStepSettings property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessingStepSettings(String value) {
        this.processingStepSettings = value;
    }

    /**
     * Gets the value of the processingSoftware property.
     * 
     * @return
     *     possible object is
     *     {@link ProcessingSoftwareType }
     *     
     */
    public ProcessingSoftwareType getProcessingSoftware() {
        return processingSoftware;
    }

    /**
     * Sets the value of the processingSoftware property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProcessingSoftwareType }
     *     
     */
    public void setProcessingSoftware(ProcessingSoftwareType value) {
        this.processingSoftware = value;
    }

}
