package nl.athena.openehr.rm.data_structures.item_structure;

import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_structures.representation.Cluster;
import nl.athena.openehr.rm.data_structures.representation.Element;
import nl.athena.openehr.rm.data_structures.representation.Item;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public class ItemTree extends ItemStructure {

    private List<Item> items;

    public boolean hasElementPath(final String aPath) {
        return false;
    }

    public List<DvText> nsmes() {
        return null;
    }

    public Element namedItem(final String aName) {
        return null;
    }

    public Cluster asHierarchy() {
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
