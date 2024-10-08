package nl.athena.openehr.base.base_types.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;

/**
 * An enumeration of three values that may commonly occur in constraint models.
 * <p>
 * Use as the type of any attribute within this model, which expresses constraint on some attribute in a class in a
 * reference model. For example to indicate validity of Date/Time fields. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_validity_kind_enumeration">
 *     VALIDITY_KIND</a> enumeration.
 */
@XmlType(name = "VALIDITY_KIND")
@XmlEnum
@Getter
public enum ValidityKind {
    /**
     * Constant to indicate mandatory presence of something.
     */
    @XmlEnumValue("mandatory")
    MANDATORY("mandatory"),

    /**
     * Constant to indicate optional presence of something.
     */
    @XmlEnumValue("optional")
    OPTIONAL("optional"),

    /**
     * Constant to indicate disallowed presence of something.
     */
    @XmlEnumValue("prohibited")
    PROHIBITED("prohibited");

    @JsonValue
    private final String value;

    /**
     * Constructor.
     *
     * @param theValue The official OpenEHR value of the enumeration.
     */
    ValidityKind(@NotNull final String theValue) {
        value = theValue;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Creates a {@link ValidityKind} from the given value string. This method is used by Jackson during JSON
     * deserialization.
     *
     * @param theValue The OpenEHR validity kind string value.
     * @return The {@link ValidityKind} for the specified value.
     */
    @JsonCreator
    public static ValidityKind fromValue(final String theValue) {
        for (ValidityKind validityKind : ValidityKind.values()) {
            if (validityKind.value.equalsIgnoreCase(theValue)) {
                return validityKind;
            }
        }

        return null;
    }

}
