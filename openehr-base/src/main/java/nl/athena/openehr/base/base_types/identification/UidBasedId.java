package nl.athena.openehr.base.base_types.identification;

public class UidBasedId extends ObjectId {

    public Uid root() {
        final int index = value.indexOf("::");
        return new Uid(value.substring(0, index));
    }

    public String extension() {
        final int index = value.indexOf("::");
        return value.substring(index + 2);
    }

    public Boolean hasExtension() {
        return !extension().isEmpty();
    }

}
