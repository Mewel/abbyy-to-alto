
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 * 				Any alternative for the word.
 * 				Alternative can outline a variant of writing by new typing / spelling rules, typically manually done or by dictionary replacements.
 * 				The above sample is an old composed character "Æ" of ancient time, which is replaced now by "Ä".
 * 				As variant are meant alternatives of the real printed content which are options outlined by the text recognition process. 
 * 				Similar sample: "Straße" vs. "Strasse". Such alternatives are not coming from text recognition.
 * 			
 * 
 * <p>Java class for ALTERNATIVEType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ALTERNATIVEType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="PURPOSE" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ALTERNATIVEType", propOrder = {
    "value"
})
public class ALTERNATIVEType {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "PURPOSE")
    protected String purpose;

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
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPURPOSE() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPURPOSE(String value) {
        this.purpose = value;
    }

}
