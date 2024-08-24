package nl.athena.openehr.base.base_types.identification;

import org.apache.commons.lang3.StringUtils;

public class LocatableRef extends ObjectRef {

    private String path;
    private UidBasedId id;

    public String asUri() {
        final int index = namespace.indexOf(":");
        final String scheme = namespace.substring(0, index);
        final String uri = scheme + ":" + id.value;

        return StringUtils.isNotBlank(path) ? uri + "/" + path : uri;
    }

}
