package nl.athena.openehr.rm.composition;

import nl.athena.openehr.rm.common.archetyped.Locatable;
import nl.athena.openehr.rm.common.archetyped.Pathable;
import nl.athena.openehr.rm.common.generic.PartyProxy;
import nl.athena.openehr.rm.composition.content.ContentItem;
import nl.athena.openehr.rm.data_types.text.CodePhrase;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

import java.util.List;

public class Composition extends Locatable {

    private CodePhrase language;
    private CodePhrase territory;
    private DvCodedText category;
    private List<ContentItem> content;
    private PartyProxy composer;
    private EventContext context;

    public boolean isPersistent() {
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
