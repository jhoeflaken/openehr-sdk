package nl.athena.openehr.base.base_types.builtins;

import jakarta.validation.constraints.NotNull;
import nl.athena.openehr.base.foundation_types.terminology.TerminologyCode;

/**
 * Quantity converter.
 */
public interface QuantityConverter {

    /**
     * Converts the given value from the given units to the given units.
     *
     * @param theValue     The value to convert.
     * @param theFromUnits The units to convert from.
     * @param theToUnits   The units to convert to.
     * @param theProperty  The property to convert.
     * @return The converted value.
     */
    Float convertValue(@NotNull final Float theValue,
                       @NotNull final String theFromUnits,
                       @NotNull final String theToUnits,
                       @NotNull final TerminologyCode theProperty);

}
