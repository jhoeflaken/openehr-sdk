package nl.athena.openehr.base.foundation_types.interval;

import jakarta.validation.constraints.NotNull;

/**
 * Type representing an Interval that happens to be a point value. Provides an efficient representation that is
 * substitutable for Interval<T> where needed. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_point_interval_class">
 *     PointInterval</a> class.
 *
 * @param <T> The type of the interval.
 */
public class PointInterval<T extends Comparable<T>> extends Interval<T> {

    /**
     * Constructor for PointInterval.
     *
     * @param theValue The value.
     */
    public PointInterval(@NotNull final  T theValue) {
        super(theValue, theValue, true, true);
    }

}
