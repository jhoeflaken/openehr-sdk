package nl.athena.openehr.rm.common.resource;

import nl.athena.openehr.rm.common.generic.RevisionHistory;
import nl.athena.openehr.rm.data_types.text.CodePhrase;

import java.util.Map;

public class AuthoredResource {

    private CodePhrase originalLanguage;
    private Boolean isControlled;
    private Map<String, TranslationDetails> translations;
    private ResourceDescription description;
    private RevisionHistory revisionHistory;

}
