package nl.athena.openehr.base.base_types.identification;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class ObjectVersionId extends UidBasedId {


    public ObjectVersionId(String theValue) {
        super(theValue);
    }

    public Uid objectId() {
        final String[] parts = value.split("::");
        return new Uid(parts[0]);
    }

    public Uid creatingSystemId() {
        final String[] parts = value.split("::");
        if (parts.length >= 2) {
            return new Uid(parts[1]);
        }

        return null;
    }

    public VersionTreeId versionTreeId() {
        final String[] parts = value.split("::");
        if (parts.length >= 3) {
            return new VersionTreeId(parts[2]);
        }

        return null;
    }

    public Boolean isBranch() {
        return false;
    }

}
