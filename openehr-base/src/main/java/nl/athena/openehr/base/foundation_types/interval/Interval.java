package nl.athena.openehr.base.foundation_types.interval;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

/**
 * Interval abstraction, featuring upper and lower limits that may be open or closed, included or not included.
 * Interval of ordered items. See <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_interval_class">
 * Interval</a> class.
 *
 * @param <T> The type of the interval.
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
public class Interval<T extends Comparable<T>> {

    @Nullable
    @JsonProperty("lower")
    @XmlElement(name = "lower")
    protected T lower;

    @Nullable
    @JsonProperty("upper")
    @XmlElement(name = "upper")
    protected T upper;

    @JsonProperty(value = "lower_included", required = true)
    @XmlAttribute(name = "lower_included")
    protected boolean lowerIncluded;

    @JsonProperty(value = "upper_included", required = true)
    @XmlAttribute(name = "upper_included")
    protected boolean upperIncluded;

    @JsonProperty(value = "lower_unbounded", required = true)
    @XmlAttribute(name = "lower_unbounded", required = true)
    protected boolean lowerUnbounded;

    @JsonProperty(value = "upper_unbounded", required = true)
    @XmlAttribute(name = "upper_unbounded", required = true)
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
            @Nullable final T theLower,
            @Nullable final T theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        lower = theLower;
        upper = theUpper;
        lowerIncluded = theLowerIncluded || theLower == theUpper;
        upperIncluded = theUpperIncluded || theUpper == theLower;
        lowerUnbounded = theLower == null;
        upperUnbounded = theUpper == null;

        // Check if the interval is valid.
        if (lower != null && upper != null && lower.compareTo(upper) > 0) {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_INTERVAL));
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
        boolean lowerCheck = lower == null || (lowerIncluded ? theValue.compareTo(lower) >= 0 : theValue.compareTo(lower) > 0);
        boolean upperCheck = upper == null || (upperIncluded ? theValue.compareTo(upper) <= 0 : theValue.compareTo(upper) < 0);
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
        boolean lowerOverlap = (lower == null || theInterval.upper == null ||
                (theInterval.upperIncluded  ? theInterval.upper.compareTo(lower) >= 0 : theInterval.upper.compareTo(lower) > 0));

        boolean upperOverlap = (upper == null || theInterval.lower == null ||
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
        boolean lowerContains = (lower == null || (!(theInterval.lower == null) &&
                (lowerIncluded ? lower.compareTo(theInterval.lower) <= 0 : lower.compareTo(theInterval.lower) < 0)));

        boolean upperContains = (upper == null || (!(theInterval.upper == null) &&
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
                lower == that.lower &&
                upper == that.upper;
    }

    @JsonProperty("_type")
    public String getType() {
        return "INTERVAL";
    }

}
