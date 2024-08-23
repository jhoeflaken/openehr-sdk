package nl.athena.openehr.base.base_types.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;

/**
 * An enumeration of three values that may commonly occur in constraint models.
 * <p>
 * Use as the type of any attribute within this model, which expresses constraint on some attribute in a class in a
 * reference model. For example to indicate validity of Date/Time fields.
 */
public enum ValidityKind {
    MANDATORY("mandatory"),
    OPTIONAL("optional"),
    PROHIBITED("prohibited");

    private final String value;

    /**
     * Constructor.
     *
     * @param theValue The official OpenEHR value of the enumeration.
     */
    ValidityKind(@NotNull final String theValue) {
        value = theValue;
    }

    /**
     * Returns the official OpenEHR value of the enumeration.
     *
     * @return The official OpenEHR value of the enumeration.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Creates a {@link ValidityKind} from the given value string.
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
