package nl.athena.openehr.rm.ehr.data_types.quantity;

import nl.athena.openehr.rm.ehr.data_types.text.DvText;

public class ReferenceRange<T extends DvOrdered<T>> {

    private DvText meaning;
    private DvInterval<T> range;

    public boolean isInRange(final T theValue) {
        return range.has(theValue);
    }

}
