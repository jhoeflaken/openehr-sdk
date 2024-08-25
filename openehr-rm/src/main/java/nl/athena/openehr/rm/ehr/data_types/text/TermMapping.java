package nl.athena.openehr.rm.ehr.data_types.text;

public class TermMapping {

    private char match;
    private DvCodedText purpose;
    private CodePhrase target;

    public boolean narrower() {
        return false;
    }

    public boolean broader() {
        return false;
    }

    public boolean equivalence() {
        return false;
    }

    public boolean unknown() {
        return false;
    }

    public boolean isValidMatchCode(final char c) {
        return false;
    }

}
