package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.foundation_types.time.Iso8601Date;
import nl.athena.openehr.base.foundation_types.time.Iso8601DateTime;

/**
 * Interval of {@link Iso8601Date}.
 */
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "IntervalOfDate")
public class IntervalOfDate extends Interval<Iso8601Date> {

    /**
     * Creates an interval of date.
     *
     * @param theLower         The lower bound of the interval.
     * @param theUpper         The upper bound of the interval.
     * @param theLowerIncluded Whether the lower bound is included in the interval.
     * @param theUpperIncluded Whether the upper bound is included in the interval.
     */
    public IntervalOfDate(
            @Nullable final Iso8601Date theLower,
            @Nullable final Iso8601Date theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }
}
