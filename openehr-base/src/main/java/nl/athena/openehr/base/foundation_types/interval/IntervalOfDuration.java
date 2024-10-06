package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.foundation_types.time.Iso8601Date;
import nl.athena.openehr.base.foundation_types.time.Iso8601Duration;

/**
 * Interval of {@link Iso8601Duration}.
 */
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "IntervalOfDuration")
public class IntervalOfDuration extends Interval<Iso8601Duration> {

    /**
     * Creates an interval of duration.
     *
     * @param theLower         The lower bound of the interval.
     * @param theUpper         The upper bound of the interval.
     * @param theLowerIncluded Whether the lower bound is included in the interval.
     * @param theUpperIncluded Whether the upper bound is included in the interval.
     */
    public IntervalOfDuration(
            @Nullable final Iso8601Duration theLower,
            @Nullable final Iso8601Duration theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }
}
