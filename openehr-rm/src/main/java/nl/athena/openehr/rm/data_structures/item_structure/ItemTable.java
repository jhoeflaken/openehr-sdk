package nl.athena.openehr.rm.data_structures.item_structure;

import nl.athena.openehr.rm.data_structures.representation.Cluster;
import nl.athena.openehr.rm.data_structures.representation.Element;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public class ItemTable {

    private List<Cluster> rows;

    public Integer rowCount() {
        return null;
    }

    public Integer columnCount() {
        return null;
    }

    public List<DvText> rowNames() {
        return null;
    }

    public List<DvText> columnNames() {
        return null;
    }

    public Cluster ithRow(final Integer i) {
        return null;
    }

    public boolean hasRowWithName(final String aName) {
        return false;
    }

    public boolean hasColumnWithName(final String aName) {
        return false;
    }

    public Cluster namedRow(final String aName) {
        return null;
    }

    public boolean hasRowWithKey(final List<String> keys) {
        return false;
    }

    public Cluster rowWithKey(final List<String> keys) {
        return null;
    }

    public Element elementAtCellIJ(final Integer i, final Integer j) {
        return null;
    }

    public Cluster asHierarchy() {
        return null;
    }

}
