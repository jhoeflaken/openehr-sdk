package nl.athena.openehr.rm.data_types.quantity;

import nl.athena.openehr.base.foundation_types.interval.Interval;

public class DvInterval<T extends DvOrdered<T>> extends Interval<T> {
    /**
     * Constructor for Interval.
     *
     * @param theLower         The lower limit.
     * @param theUpper         The upper limit.
     * @param theLowerIncluded True if the lower limit is included.
     * @param theUpperIncluded True if the upper limit is included.
     */
    public DvInterval(T theLower, T theUpper, boolean theLowerIncluded, boolean theUpperIncluded) {
        super(theLower, theUpper, theLowerIncluded, theUpperIncluded);
    }

}
