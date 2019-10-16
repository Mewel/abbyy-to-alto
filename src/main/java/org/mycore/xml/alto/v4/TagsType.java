
package org.mycore.xml.alto.v4;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				There are following variation of tag types available:
 * 				LayoutTag – criteria about arrangement or graphical appearance
 * 				StructureTag – criteria about grouping or formation
 * 				RoleTag – criteria about function or mission
 * 				NamedEntityTag – criteria about assignment of terms to their relationship / meaning (NER)
 * 				OtherTag – criteria about any other characteristic not listed above, the TYPE attribute is intended to be used for classification within those.
 * 			
 * 
 * <p>Java class for TagsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TagsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element name="LayoutTag" type="{http://www.loc.gov/standards/alto/ns-v4#}TagType"/>
 *           &lt;element name="StructureTag" type="{http://www.loc.gov/standards/alto/ns-v4#}TagType"/>
 *           &lt;element name="RoleTag" type="{http://www.loc.gov/standards/alto/ns-v4#}TagType"/>
 *           &lt;element name="NamedEntityTag" type="{http://www.loc.gov/standards/alto/ns-v4#}TagType"/>
 *           &lt;element name="OtherTag" type="{http://www.loc.gov/standards/alto/ns-v4#}TagType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TagsType", propOrder = {
    "layoutTagOrStructureTagOrRoleTag"
})
public class TagsType {

    @XmlElementRefs({
        @XmlElementRef(name = "StructureTag", namespace = "http://www.loc.gov/standards/alto/ns-v4#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "LayoutTag", namespace = "http://www.loc.gov/standards/alto/ns-v4#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "RoleTag", namespace = "http://www.loc.gov/standards/alto/ns-v4#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "NamedEntityTag", namespace = "http://www.loc.gov/standards/alto/ns-v4#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "OtherTag", namespace = "http://www.loc.gov/standards/alto/ns-v4#", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<TagType>> layoutTagOrStructureTagOrRoleTag;

    /**
     * Gets the value of the layoutTagOrStructureTagOrRoleTag property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the layoutTagOrStructureTagOrRoleTag property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLayoutTagOrStructureTagOrRoleTag().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TagType }{@code >}
     * {@link JAXBElement }{@code <}{@link TagType }{@code >}
     * {@link JAXBElement }{@code <}{@link TagType }{@code >}
     * {@link JAXBElement }{@code <}{@link TagType }{@code >}
     * {@link JAXBElement }{@code <}{@link TagType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<TagType>> getLayoutTagOrStructureTagOrRoleTag() {
        if (layoutTagOrStructureTagOrRoleTag == null) {
            layoutTagOrStructureTagOrRoleTag = new ArrayList<JAXBElement<TagType>>();
        }
        return this.layoutTagOrStructureTagOrRoleTag;
    }

}
