package nl.athena.openehr.rm.data_types.encapsulated;

import nl.athena.openehr.rm.data_types.text.CodePhrase;
import nl.athena.openehr.rm.data_types.url.DvUri;

public class DvMultimedia extends DvEncapsulated {

    private String alternateText;
    private DvUri uri;
    private byte[] data;
    private CodePhrase mediaType;
    private CodePhrase compressionAlgorithm;
    private byte[] integrityCheck;
    private CodePhrase integrityCheckAlgorithm;
    private Integer size;
    private DvMultimedia thumbnail;

    public boolean isExternal() {
        return false;
    }

    public boolean isInline() {
        return false;
    }

    public boolean isCompressed() {
        return false;
    }

    public boolean hasIntegrityCheck() {
        return false;
    }

}
