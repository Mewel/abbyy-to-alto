
package org.mycore.xml.alto.v4;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MeasurementUnitType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="MeasurementUnitType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="pixel"/>
 *     &lt;enumeration value="mm10"/>
 *     &lt;enumeration value="inch1200"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "MeasurementUnitType")
@XmlEnum
public enum MeasurementUnitType {

    @XmlEnumValue("pixel")
    PIXEL("pixel"),
    @XmlEnumValue("mm10")
    MM_10("mm10"),
    @XmlEnumValue("inch1200")
    INCH_1200("inch1200");
    private final String value;

    MeasurementUnitType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MeasurementUnitType fromValue(String v) {
        for (MeasurementUnitType c: MeasurementUnitType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
