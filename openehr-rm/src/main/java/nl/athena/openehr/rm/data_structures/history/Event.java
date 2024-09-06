package nl.athena.openehr.rm.data_structures.history;

import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDuration;

public abstract class Event<T extends ItemStructure> {

    private DvDateTime time;
    private ItemStructure state;
    private T data;

    public DvDuration offset() {
        return null;
    }

}
