
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for altoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="altoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" type="{http://www.loc.gov/standards/alto/ns-v4#}DescriptionType" minOccurs="0"/>
 *         &lt;element name="Styles" type="{http://www.loc.gov/standards/alto/ns-v4#}StylesType" minOccurs="0"/>
 *         &lt;element name="Tags" type="{http://www.loc.gov/standards/alto/ns-v4#}TagsType" minOccurs="0"/>
 *         &lt;element name="Layout" type="{http://www.loc.gov/standards/alto/ns-v4#}LayoutType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SCHEMAVERSION" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "altoType", propOrder = {
    "description",
    "styles",
    "tags",
    "layout"
})
public class AltoType {

    @XmlElement(name = "Description")
    protected DescriptionType description;
    @XmlElement(name = "Styles")
    protected StylesType styles;
    @XmlElement(name = "Tags")
    protected TagsType tags;
    @XmlElement(name = "Layout", required = true)
    protected LayoutType layout;
    @XmlAttribute(name = "SCHEMAVERSION")
    protected String schemaversion;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptionType }
     *     
     */
    public DescriptionType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptionType }
     *     
     */
    public void setDescription(DescriptionType value) {
        this.description = value;
    }

    /**
     * Gets the value of the styles property.
     * 
     * @return
     *     possible object is
     *     {@link StylesType }
     *     
     */
    public StylesType getStyles() {
        return styles;
    }

    /**
     * Sets the value of the styles property.
     * 
     * @param value
     *     allowed object is
     *     {@link StylesType }
     *     
     */
    public void setStyles(StylesType value) {
        this.styles = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link TagsType }
     *     
     */
    public TagsType getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link TagsType }
     *     
     */
    public void setTags(TagsType value) {
        this.tags = value;
    }

    /**
     * Gets the value of the layout property.
     * 
     * @return
     *     possible object is
     *     {@link LayoutType }
     *     
     */
    public LayoutType getLayout() {
        return layout;
    }

    /**
     * Sets the value of the layout property.
     * 
     * @param value
     *     allowed object is
     *     {@link LayoutType }
     *     
     */
    public void setLayout(LayoutType value) {
        this.layout = value;
    }

    /**
     * Gets the value of the schemaversion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSCHEMAVERSION() {
        return schemaversion;
    }

    /**
     * Sets the value of the schemaversion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSCHEMAVERSION(String value) {
        this.schemaversion = value;
    }

}
