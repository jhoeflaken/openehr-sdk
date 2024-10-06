package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.foundation_types.time.Iso8601DateTime;

/**
 * Interval of {@link Iso8601DateTime}.
 */
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "IntervalOfDateTime")
public class IntervalOfDateTime extends Interval<Iso8601DateTime> {

    /**
     * Creates an interval of date and time.
     *
     * @param theLower         The lower bound of the interval.
     * @param theUpper         The upper bound of the interval.
     * @param theLowerIncluded Whether the lower bound is included in the interval.
     * @param theUpperIncluded Whether the upper bound is included in the interval.
     */
    public IntervalOfDateTime(
            @Nullable final Iso8601DateTime theLower,
            @Nullable final Iso8601DateTime theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }
}
