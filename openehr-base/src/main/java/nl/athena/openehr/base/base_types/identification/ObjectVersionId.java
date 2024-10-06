package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Globally unique identifier for one version of a versioned object; lexical form: object_id '::' creating_system_id
 * '::' version_tree_id.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_object_version_id_class">ObjectVersionId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OBJECT_VERSION_ID")
public class ObjectVersionId extends UidBasedId {

    /**
     * Constructs a new ObjectVersionId with the given value.
     *
     * @param theValue The value of the ObjectVersionId.
     */
    public ObjectVersionId(@Nonnull final String theValue) {
        super(theValue);
    }

    /**
     * Returns the object id part of the ObjectVersionId.
     *
     * @return the object id part of the ObjectVersionId.
     */
    public Uid objectId() {
        final String[] parts = value.split("::");
        return Uuid.of(parts[0]);
    }

    /**
     * Returns the creating system id part of the ObjectVersionId.
     *
     * @return the creating system id part of the ObjectVersionId.
     */
    public Uid creatingSystemId() {
        final String[] parts = value.split("::");
        if (parts.length >= 2) {
            return InternetId.of(parts[1]);
        }

        return null;
    }

    /**
     * Returns the version tree id part of the ObjectVersionId.
     *
     * @return the version tree id part of the ObjectVersionId.
     */
    public VersionTreeId versionTreeId() {
        final String[] parts = value.split("::");
        if (parts.length >= 3) {
            return new VersionTreeId(parts[2]);
        }

        return null;
    }

    /**
     * Whether this ObjectVersionId is a branch.
     *
     * @return Whether this ObjectVersionId is a branch.
     */
    public Boolean isBranch() {
        return false;
    }

    @Override
    @JsonProperty(value = "_type")
    public String getType() {
        return "OBJECT_VERSION_ID";
    }

}
