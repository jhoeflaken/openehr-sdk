package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nonnull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * An Interval of Integer, used to represent multiplicity, cardinality and optionality in models. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_multiplicity_interval_class">
 *     MultiplicityInterval</a> class.
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class MultiplicityInterval extends Interval<Integer> {

    public static final String MULTIPLICITY_RANGE_MARKER = "..";
    public static final String MULTIPLICITY_UNBOUNDED_MARKER = "*";

    /**
     * Constructor for Interval.
     *
     * @param theLower         The lower limit.
     * @param theUpper         The upper limit.
     */
    public MultiplicityInterval(
            @Nonnull final Integer theLower,
            @Nonnull final Integer theUpper) {
        super(theLower, theUpper, true, true);
    }

    /**
     * Check if the interval is open, the upper bound is unbounded.
     *
     * @return True if the interval is open, i.e. the upper bound is unbounded.
     */
    public boolean isOpen() {
        return upperUnbounded;
    }

    /**
     * Check if the interval is optional, i.e. lower bound is 0.
     *
     * @return True if the interval is optional.
     */
    public boolean isOptional() {
        return lower == null || lower == 0;
    }

    /**
     * Check if the interval is mandatory, i.e. lower bound is >= 1.
     *
     * @return True if the interval is mandatory.
     */
    public boolean isMandatory() {
        return lower != null && lower >= 1;
    }

    /**
     * Check if the interval is prohibited, i.e. lower bound is 0 and upper bound is 0.
     *
     * @return True if the interval is prohibited.
     */
    public boolean isProhibited() {
        return lower != null && lower == 0 && upper != null && upper == 0;
    }

    @Override
    public String toString() {
        String lowerBound = lower == null ? MULTIPLICITY_UNBOUNDED_MARKER : lower.toString();
        String upperBound = upper == null ? MULTIPLICITY_UNBOUNDED_MARKER : upper.toString();
        String lowerBracket = lowerIncluded ? "[" : ")";
        String upperBracket = upperIncluded ? "]" : ")";
        return lowerBracket + lowerBound + MULTIPLICITY_RANGE_MARKER + upperBound + upperBracket;
    }

}
