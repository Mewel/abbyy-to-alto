
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fontTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="fontTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="serif"/>
 *     &lt;enumeration value="sans-serif"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "fontTypeType")
@XmlEnum
public enum FontTypeType {

    @XmlEnumValue("serif")
    SERIF("serif"),
    @XmlEnumValue("sans-serif")
    SANS_SERIF("sans-serif");
    private final String value;

    FontTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static FontTypeType fromValue(String v) {
        for (FontTypeType c: FontTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
