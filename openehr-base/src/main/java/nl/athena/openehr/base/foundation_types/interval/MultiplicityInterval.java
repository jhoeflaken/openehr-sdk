package nl.athena.openehr.base.foundation_types.interval;

/**
 * An Interval of Integer, used to represent multiplicity, cardinality and optionality in models. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_multiplicity_interval_class">
 *     MultiplicityInterval</a> class.
 */
public class MultiplicityInterval extends ProperInterval<Integer> {

    public static final String MULTIPLICITY_RANGE_MARKER = "..";
    public static final String MULTIPLICITY_UNBOUNDED_MARKER = "*";

    /**
     * Constructor for Interval.
     *
     * @param theLower         The lower limit.
     * @param theUpper         The upper limit.
     */
    public MultiplicityInterval(
            final Integer theLower,
            final Integer theUpper) {
        super(theLower, theUpper, true, true);
    }

    /**
     * Check if the interval is open, the upper bound is unbounded.
     *
     * @return True if the inerval is open, i.e. the upper bound is unbounded.
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
        return lower == 0;
    }

    /**
     * Check if the interval is mandatory, i.e. lower bound is >= 1.
     *
     * @return True if the interval is mandatory.
     */
    public boolean isMandatory() {
        return lower >= 1;
    }

    /**
     * Check if the interval is prohibited, i.e. lower bound is 0 and upper bound is 0.
     *
     * @return True if the interval is prohibited.
     */
    public boolean isProhibited() {
        return lower == 0 && upper == 0;
    }

    @Override
    public String toString() {
        String lowerBound = lowerUnbounded ? MULTIPLICITY_UNBOUNDED_MARKER : lower.toString();
        String upperBound = upperUnbounded ? MULTIPLICITY_UNBOUNDED_MARKER : upper.toString();
        String lowerBracket = lowerIncluded ? "[" : ")";
        String upperBracket = upperIncluded ? "]" : ")";
        return lowerBracket + lowerBound + MULTIPLICITY_RANGE_MARKER + upperBound + upperBracket;
    }

}
