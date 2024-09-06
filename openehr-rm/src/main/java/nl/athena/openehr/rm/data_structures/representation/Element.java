package nl.athena.openehr.rm.data_structures.representation;

import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_types.DataValue;
import nl.athena.openehr.rm.data_types.text.DvCodedText;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public class Element extends Item {

    private DvCodedText nullFlavour;
    private DvText nullReason;
    private DataValue value;

    public boolean isNull() {
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
