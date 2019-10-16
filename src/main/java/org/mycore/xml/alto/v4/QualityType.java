
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QualityType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="QualityType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OK"/>
 *     &lt;enumeration value="Missing"/>
 *     &lt;enumeration value="Missing in original"/>
 *     &lt;enumeration value="Damaged"/>
 *     &lt;enumeration value="Retained"/>
 *     &lt;enumeration value="Target"/>
 *     &lt;enumeration value="As in original"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "QualityType")
@XmlEnum
public enum QualityType {

    OK("OK"),
    @XmlEnumValue("Missing")
    MISSING("Missing"),
    @XmlEnumValue("Missing in original")
    MISSING_IN_ORIGINAL("Missing in original"),
    @XmlEnumValue("Damaged")
    DAMAGED("Damaged"),
    @XmlEnumValue("Retained")
    RETAINED("Retained"),
    @XmlEnumValue("Target")
    TARGET("Target"),
    @XmlEnumValue("As in original")
    AS_IN_ORIGINAL("As in original");
    private final String value;

    QualityType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static QualityType fromValue(String v) {
        for (QualityType c: QualityType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
