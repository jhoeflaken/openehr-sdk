package nl.athena.openehr.rm.demopgraphic;

import nl.athena.openehr.rm.common.archetyped.Locatable;
import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.DvInterval;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDate;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public class PartyRelationship extends Locatable {

    private ItemStructure details;
    private DvInterval<DvDate> timeValidity;


    public DvText type() {
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
