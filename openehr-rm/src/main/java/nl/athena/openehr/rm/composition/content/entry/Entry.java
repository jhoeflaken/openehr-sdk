package nl.athena.openehr.rm.composition.content.entry;

import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.composition.content.ContentItem;
import nl.athena.openehr.rm.data_types.text.CodePhrase;

public abstract class Entry extends ContentItem {

    private CodePhrase language;
    private CodePhrase encoding;
    private ObjectRef workflowId;

    public boolean subjectIsSelf() {
        return false;
    }

}
