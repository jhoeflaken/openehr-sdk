package nl.athena.openehr.rm.data_structures.item_structure;

import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_structures.representation.Element;

import java.util.List;

public class ItemSingle extends ItemStructure {

    private Element item;

    public Element asHierarchy() {
        return null;
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
