package nl.athena.openehr.base.foundation_types.interval;

import jakarta.validation.constraints.NotNull;

/**
 * Interval abstraction, featuring upper and lower limits that may be open or closed, included or not included.
 * Interval of ordered items. See <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_interval_class">
 * Interval</a> class.
 *
 * @param <T> The type of the interval.
 */
public abstract class Interval<T extends Comparable<T>> {

    protected T lower;
    protected T upper;
    protected boolean lowerIncluded;
    protected boolean upperIncluded;
    protected boolean lowerUnbounded;
    protected boolean upperUnbounded;

    /**
     * Constructor for Interval.
     *
     * @param theLower         The lower limit.
     * @param theUpper         The upper limit.
     * @param theLowerIncluded True if the lower limit is included.
     * @param theUpperIncluded True if the upper limit is included.
     */
    public Interval(
            final T theLower,
            final T theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        lower = theLower;
        upper = theUpper;
        lowerIncluded = theLowerIncluded || theLower == theUpper;
        upperIncluded = theUpperIncluded || theUpper == theLower;
        lowerUnbounded = theLower == null;
        upperUnbounded = theUpper == null;

        if (lower != null && upper != null && lower.compareTo(upper) > 0) {
            throw new IllegalArgumentException("Lower limit must be less than or equal to upper limit");
        }
    }

    /**
     * True if the specified value is properly contained in this Interval. True if (lower_unbounded or lower_included
     * and value >= lower) or v > lower and (upper_unbounded or upper_included and v <= upper or v < upper).
     *
     * @param theValue The value to check.
     * @return True if the value is contained in this Interval.
     */
    public boolean has(@NotNull final T theValue) {
        boolean lowerCheck = lowerUnbounded || (lowerIncluded ? theValue.compareTo(lower) >= 0 : theValue.compareTo(lower) > 0);

        boolean upperCheck = upperUnbounded || (upperIncluded ? theValue.compareTo(upper) <= 0 : theValue.compareTo(upper) < 0);
        return lowerCheck && upperCheck;
    }

    /**
     * True if there is any overlap between this and the specified intervals. True if at least one limit of the
     * specified interval is strictly inside the limits of this interval.
     *
     * @param theInterval The Interval to check.
     * @return True if the Interval is contained in this Interval.
     */
    public boolean intersects(@NotNull final Interval<T> theInterval) {
        boolean lowerOverlap = (lowerUnbounded || theInterval.upperUnbounded ||
                (theInterval.upperIncluded ? theInterval.upper.compareTo(lower) >= 0 : theInterval.upper.compareTo(lower) > 0));

        boolean upperOverlap = (upperUnbounded || theInterval.lowerUnbounded ||
                (theInterval.lowerIncluded ? theInterval.lower.compareTo(upper) <= 0 : theInterval.lower.compareTo(upper) < 0));

        return lowerOverlap && upperOverlap;
    }

    /**
     * True if current interval properly contains the specified interval. True if all points of the specified interval
     * are inside the current interval.
     *
     * @param theInterval The Interval to check.
     * @return True if the Interval is contained in this Interval.
     */
    public boolean contains(@NotNull final Interval<T> theInterval) {
        boolean lowerContains = (lowerUnbounded || (!theInterval.lowerUnbounded &&
                (lowerIncluded ? lower.compareTo(theInterval.lower) <= 0 : lower.compareTo(theInterval.lower) < 0)));

        boolean upperContains = (upperUnbounded || (!theInterval.upperUnbounded &&
                (upperIncluded ? upper.compareTo(theInterval.upper) >= 0 : upper.compareTo(theInterval.upper) > 0)));

        return lowerContains && upperContains;
    }

    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }

        if (theOther == null || getClass() != theOther.getClass()) {
            return false;
        }

        final Interval<?> that = (Interval<?>) theOther;
        return lowerIncluded == that.lowerIncluded &&
                upperIncluded == that.upperIncluded &&
                lowerUnbounded == that.lowerUnbounded &&
                upperUnbounded == that.upperUnbounded &&
                lower.equals(that.lower) &&
                upper.equals(that.upper);
    }

    /**
     * Get the lower limit of the interval. Indicates an unbounded lower limit if null.
     *
     * @return The lower limit of the interval. Indicates a unbounded lower limit if null.
     */
    public T getLower() {
        return lower;
    }

    /**
     * Get the upper limit of the interval. Indicates an unbounded upper limit if null.
     *
     * @return The upper limit of the interval. Indicates an unbounded upper limit if null.
     */
    public T getUpper() {
        return upper;
    }

    /**
     * Check whether the lower limit is included in the interval.
     *
     * @return True if the lower limit is included in the interval.
     */
    public boolean isLowerIncluded() {
        return lowerIncluded;
    }

    /**
     * Check whether the upper limit is included in the interval.
     *
     * @return True if the upper limit is included in the interval.
     */
    public boolean isUpperIncluded() {
        return upperIncluded;
    }

    /**
     * Check whether the lower limit is unbounded.
     *
     * @return True if the lower limit is unbounded.
     */
    public boolean isLowerUnbounded() {
        return lowerUnbounded;
    }

    /**
     * Check whether the upper limit is unbounded.
     *
     * @return True if the upper limit is unbounded.
     */
    public boolean isUpperUnbounded() {
        return upperUnbounded;
    }

}
