package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

public class UidBasedId extends ObjectId {

    @JsonIgnore
    private final Uid root;

    @JsonIgnore
    private final String extension;

    public UidBasedId(String theValue) {
        super(theValue);

        final int index = value.indexOf("::");
        root = new Uid(value.substring(0, index));
        if (index > 0) {
            extension = value.substring(index + 2);
        } else {
            extension = null;
        }
    }

    public Uid root() {
        return root;
    }

    public String extension() {
        return extension;
    }

    public Boolean hasExtension() {
        return StringUtils.isNotBlank(extension);
    }

}
