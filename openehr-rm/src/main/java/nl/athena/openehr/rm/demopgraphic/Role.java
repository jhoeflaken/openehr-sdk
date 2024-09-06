package nl.athena.openehr.rm.demopgraphic;

import nl.athena.openehr.base.base_types.identification.PartyRef;
import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.data_types.quantity.DvInterval;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvTime;

import java.util.List;

public class Role  extends Party {

    private DvInterval<DvTime> timeValidity;
    private PartyRef performer;
    private List<Capability> capabilities;

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
