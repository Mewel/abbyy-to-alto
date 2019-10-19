
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LayoutType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LayoutType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Page" type="{http://www.loc.gov/standards/alto/ns-v4#}PageType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="STYLEREFS" type="{http://www.w3.org/2001/XMLSchema}IDREFS" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LayoutType", propOrder = {
    "page"
})
public class LayoutType {

    @XmlElement(name = "Page", required = true)
    protected List<PageType> page;
    @XmlAttribute(name = "STYLEREFS")
    @XmlIDREF
    @XmlSchemaType(name = "IDREFS")
    protected List<Object> stylerefs;

    /**
     * Gets the value of the page property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the page property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PageType }
     * 
     * 
     */
    public List<PageType> getPage() {
        if (page == null) {
            page = new ArrayList<PageType>();
        }
        return this.page;
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

}
