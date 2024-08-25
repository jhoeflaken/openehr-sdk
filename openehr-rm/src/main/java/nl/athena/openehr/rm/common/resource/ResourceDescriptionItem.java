package nl.athena.openehr.rm.common.resource;

import nl.athena.openehr.rm.data_types.text.CodePhrase;

import java.util.List;
import java.util.Map;

public class ResourceDescriptionItem {

    private CodePhrase language;
    private String purpose;
    private List<String> keywords;
    private String use;
    private String misuse;
    private String copyright;
    private Map<String, String> originalResourceUri;
    private Map<String, String> otherDetails;

}
