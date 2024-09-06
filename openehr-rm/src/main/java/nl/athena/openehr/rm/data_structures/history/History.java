package nl.athena.openehr.rm.data_structures.history;

import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_structures.DataStructure;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDuration;

import java.util.List;

public class History<T extends ItemStructure> extends DataStructure {

    private DvDateTime origin;
    private DvDuration period;
    private DvDuration duration;

    public boolean isPeriodic() {
        return false;
    }

    @Override
    public Pathable parent() {
        return null;
    }

    @Override
    public Object itemAtPath(String thePath) {
        return null;
    }

    @Override
    public List<Object> itemsAtPath(String thePath) {
        return List.of();
    }

    @Override
    public boolean pathExists(String thePath) {
        return false;
    }

    @Override
    public boolean pathUnique(String thePath) {
        return false;
    }

    @Override
    public String pathOfItem(Object theItem) {
        return "";
    }

}
