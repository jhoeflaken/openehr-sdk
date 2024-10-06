package nl.athena.openehr.base.foundation_types.interval;

import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Interval of integer numbers.
 */
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "IntervalOfInteger")
public class IntervalOfInteger  extends Interval<Integer> {

    /**
     * Creates an interval of integer numbers.
     *
     * @param theLower         The lower bound of the interval.
     * @param theUpper         The upper bound of the interval.
     * @param theLowerIncluded Whether the lower bound is included in the interval.
     * @param theUpperIncluded Whether the upper bound is included in the interval.
     */
    public IntervalOfInteger(
            @Nullable final Integer theLower,
            @Nullable final Integer theUpper,
            final boolean theLowerIncluded,
            final boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }

}
