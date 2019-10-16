
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 *  This identifier must be unique within the local system. 
 * 			To facilitate file sharing or interoperability with other systems, documentIdentifierLocation may be added to designate the system or application where the identifier is unique.
 * 
 * <p>Java class for documentIdentifierType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentIdentifierType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.loc.gov/standards/alto/ns-v4#>documentIdentifierValueType">
 *       &lt;attribute name="documentIdentifierLocation" type="{http://www.loc.gov/standards/alto/ns-v4#}documentIdentifierLocationValueType" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentIdentifierType", propOrder = {
    "value"
})
public class DocumentIdentifierType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "documentIdentifierLocation")
    protected String documentIdentifierLocation;

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
     * Gets the value of the documentIdentifierLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentIdentifierLocation() {
        return documentIdentifierLocation;
    }

    /**
     * Sets the value of the documentIdentifierLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentIdentifierLocation(String value) {
        this.documentIdentifierLocation = value;
    }

}
