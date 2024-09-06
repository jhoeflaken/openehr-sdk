package nl.athena.openehr.rm.data_structures.history;

import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDuration;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

public class IntervalEvent<T extends ItemStructure> extends Event<T> {

    private DvDuration width;
    private Integer sampleCount;
    private DvCodedText mathFunction;

    public DvDateTime intervalStartTime() {
        return null;
    }

}
