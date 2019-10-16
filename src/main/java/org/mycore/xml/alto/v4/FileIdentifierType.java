
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 *  This identifier must be unique within the local system. 
 * 			To facilitate file sharing or interoperability with other systems, fileIdentifierLocation may be added to designate the system or application where the identifier is unique.
 * 
 * <p>Java class for fileIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fileIdentifierType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.loc.gov/standards/alto/ns-v4#>fileIdentifierValueType">
 *       &lt;attribute name="fileIdentifierLocation" type="{http://www.loc.gov/standards/alto/ns-v4#}fileIdentifierLocationValueType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fileIdentifierType", propOrder = {
    "value"
})
public class FileIdentifierType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "fileIdentifierLocation")
    protected String fileIdentifierLocation;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the fileIdentifierLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileIdentifierLocation() {
        return fileIdentifierLocation;
    }

    /**
     * Sets the value of the fileIdentifierLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileIdentifierLocation(String value) {
        this.fileIdentifierLocation = value;
    }

}
