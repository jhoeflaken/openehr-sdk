package nl.athena.openehr.rm.common.resource;

import nl.athena.openehr.rm.data_types.text.CodePhrase;

import java.util.Map;

public class TranslationDetails {

    private CodePhrase language;
    private Map<String, String> author;
    private String accreditation;
    private Map<String, String> otherDetails;

}
