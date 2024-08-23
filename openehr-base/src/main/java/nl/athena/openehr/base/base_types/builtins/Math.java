package nl.athena.openehr.base.base_types.builtins;

import jakarta.validation.constraints.NotNull;

/**
 * Math functions.
 */
public interface Math {

    /**
     * Returns the natural logarithm of the given number.
     *
     * @param theNumber The number to calculate the natural logarithm of.
     *                  Must not be null.
     *
     * @return The natural logarithm of the given number.
     */
    @NotNull
    Double ln(@NotNull final  Number theNumber);

    /**
     * Returns the logarithm of the given number to the given base.
     *
     * @param theNumber The number to calculate the logarithm of.
     *                  Must not be null.
     *
     * @return The logarithm of the given number to the base 10.
     */
    @NotNull
    Double log(@NotNull final Number theNumber);

    /**
     * Returns the sine of the given number.
     *
     * @param theNumber The number to calculate the sine of.
     * @return The sine of the given number.
     */
    @NotNull
    Double sin(@NotNull final Number theNumber);

}
