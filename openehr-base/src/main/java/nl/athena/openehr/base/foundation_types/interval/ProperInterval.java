package nl.athena.openehr.base.foundation_types.interval;

/**
 * Type representing a 'proper' Interval, i.e. any two-sided or one-sided interval. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_proper_interval_class">
 *     ProperInterval</a> class.
 *
 * @param <T> The type of the interval.
 */
public class ProperInterval<T extends Comparable<T>> extends Interval<T> {

    /**
     * Constructor for Interval.
     *
     * @param theLower         The lower limit.
     * @param theUpper         The upper limit.
     * @param theLowerIncluded True if the lower limit is included.
     * @param theUpperIncluded True if the upper limit is included.
     */
    public ProperInterval(
            final T theLower,
            final T theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }

}
