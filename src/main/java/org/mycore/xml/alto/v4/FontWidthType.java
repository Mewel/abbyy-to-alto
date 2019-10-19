
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fontWidthType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fontWidthType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="proportional"/>
 *     &lt;enumeration value="fixed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fontWidthType")
@XmlEnum
public enum FontWidthType {

    @XmlEnumValue("proportional")
    PROPORTIONAL("proportional"),
    @XmlEnumValue("fixed")
    FIXED("fixed");
    private final String value;

    FontWidthType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FontWidthType fromValue(String v) {
        for (FontWidthType c: FontWidthType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
