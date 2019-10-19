
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PositionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="PositionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Left"/>
 *     &lt;enumeration value="Right"/>
 *     &lt;enumeration value="Foldout"/>
 *     &lt;enumeration value="Single"/>
 *     &lt;enumeration value="Cover"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "PositionType")
@XmlEnum
public enum PositionType {

    @XmlEnumValue("Left")
    LEFT("Left"),
    @XmlEnumValue("Right")
    RIGHT("Right"),
    @XmlEnumValue("Foldout")
    FOLDOUT("Foldout"),
    @XmlEnumValue("Single")
    SINGLE("Single"),
    @XmlEnumValue("Cover")
    COVER("Cover");
    private final String value;

    PositionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PositionType fromValue(String v) {
        for (PositionType c: PositionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
