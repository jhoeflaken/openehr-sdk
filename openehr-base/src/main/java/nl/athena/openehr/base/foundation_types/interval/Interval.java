package nl.athena.openehr.base.foundation_types.interval;

import jakarta.validation.constraints.NotNull;

public class Interval<T extends Comparable<T>> {

    protected T lower;
    protected T upper;
    protected boolean lowerIncluded;
    protected boolean upperIncluded;
    protected boolean lowerUnbounded;
    protected boolean upperUnbounded;

    public boolean has(@NotNull final T theValue) {
        return false;
    }

    public boolean intersects(@NotNull final Interval<T> theInterval) {
        return false;
    }

    public boolean contains(@NotNull final Interval<T> theInterval) {
        return false;
    }

    @Override
    public boolean equals(final Object theObj) {
        if (this == theObj) {
            return true;
        }

        if (theObj == null || getClass() != theObj.getClass()) {
            return false;
        }

        final Interval<?> that = (Interval<?>) theObj;
        return lowerIncluded == that.lowerIncluded &&
                upperIncluded == that.upperIncluded &&
                lowerUnbounded == that.lowerUnbounded &&
                upperUnbounded == that.upperUnbounded &&
                lower.equals(that.lower) &&
                upper.equals(that.upper);
    }

}
