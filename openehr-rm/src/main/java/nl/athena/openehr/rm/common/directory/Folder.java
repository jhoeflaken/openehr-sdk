package nl.athena.openehr.rm.common.directory;

import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.common.archetyped.Locatable;
import nl.athena.openehr.rm.common.archetyped.Pathable;

import java.util.List;

public class Folder extends Locatable {

    private List<Folder> folders;
    private List<ObjectRef> items;

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
