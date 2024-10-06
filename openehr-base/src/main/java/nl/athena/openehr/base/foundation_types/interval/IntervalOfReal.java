package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Interval of real numbers.
 */
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "IntervalOfReal")
public class IntervalOfReal extends Interval<Float> {

    /**
     * Creates an interval of real numbers.
     *
     * @param theLower         The lower bound of the interval.
     * @param theUpper         The upper bound of the interval.
     * @param theLowerIncluded Whether the lower bound is included in the interval.
     * @param theUpperIncluded Whether the upper bound is included in the interval.
     */
    public IntervalOfReal(
            @Nullable final Float theLower,
            @Nullable final Float theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }

}
