package nl.athena.openehr.base.base_types.identification;

public class TerminologyId extends ObjectId {

    private final String name;
    private final String version;

    public TerminologyId(String theValue) {
        super(theValue);

        int versionStartIndex = theValue.indexOf('(');
        if (versionStartIndex != -1) {
            name = theValue.substring(0, versionStartIndex).trim();
            int versionEndIndex = theValue.indexOf(')', versionStartIndex);
            if (versionEndIndex != -1) {
                version = theValue.substring(versionStartIndex + 1, versionEndIndex).trim();
            } else {
                throw new IllegalArgumentException("Invalid format: missing closing parenthesis");
            }
        } else {
            name = theValue.trim();
            version = null;
        }
    }

    public String name() {
        return name;
    }

    public String versionId() {
        return version;
    }

}
