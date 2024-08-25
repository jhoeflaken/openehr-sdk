package nl.athena.openehr.rm.common.resource;

import java.util.List;
import java.util.Map;

public class ResourceDescription {

    private Map<String, String> originalAuthor;
    private List<String> otherContributors;
    private String lifecycleState;
    private String resourcePackageUri;
    private Map<String, String> otherDetails;
    private Map<String, ResourceDescriptionItem> details;

}
