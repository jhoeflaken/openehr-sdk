package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.StringUtils;

/**
 * Version tree identifier for one version. Lexical form:
 * <br/><br/>
 * trunk_version [ '.' branch_number '.' branch_version ]
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_version_tree_id_class">VersionTreeId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VERSION_TREE_ID")
public class VersionTreeId {

    @JsonProperty(value = "value", required = true)
    @XmlElement(name = "value", required = true)
    private final String value;

    /**
     * Constructs a VersionTreeId with the given value.
     *
     * @param theValue The value of the VersionTreeId.
     */
    public VersionTreeId(final String theValue) {
        value = theValue;
    }

    /**
     * Returns the type of this VersionTreeId.
     *
     * @return the type of this VersionTreeId.
     */
    public String trunkVersion() {
        final String[] parts = value.split("\\.");
        return parts[0];
    }

    /**
     * Returns the branch version of this VersionTreeId.
     *
     * @return the branch version of this VersionTreeId.
     */
    public String branchVersion() {
        final String[] parts = value.split("\\.");
        if (parts.length > 1) {
            return parts[1];
        }

        return null;
    }

    /**
     * Returns the branch number of this VersionTreeId.
     *
     * @return the branch number of this VersionTreeId.
     */
    public String branchNumber() {
        final String[] parts = value.split("\\.");
        if (parts.length > 2) {
            return parts[2];
        }

        return value;
    }

    /**
     * Whether this VersionTreeId is a branch.
     *
     * @return Whether this VersionTreeId is a branch.
     */
    public Boolean isBranch() {
        return StringUtils.isNotBlank(branchNumber()) && StringUtils.isNotBlank(branchVersion());
    }

    /**
     * Whether the value of this VersionTreeId is valid.
     *
     * @return Whether the value of this VersionTreeId is valid.
     */
    public Boolean valueValid() {
        return StringUtils.isNotBlank(value);
    }

    /**
     * Whether the trunk version of this VersionTreeId is valid.
     *
     * @return Whether the trunk version of this VersionTreeId is valid.
     */
    public Boolean TrunkVersionValid() {
        final String trunkVersion = trunkVersion();
        return StringUtils.isNotBlank(trunkVersion) && (Integer.parseInt(trunkVersion) >= 1);
    }

    /**
     * Whether the branch version of this VersionTreeId is valid.
     *
     * @return Whether the branch version of this VersionTreeId is valid.
     */
    public Boolean BranchVersionValid() {
        final String branchVersion = branchVersion();
        return StringUtils.isNotBlank(branchVersion) && (Integer.parseInt(branchVersion) >= 1);
    }

    /**
     * Whether the branch of this VersionTreeId is valid.
     *
     * @return Whether the branch of this VersionTreeId is valid.
     */
    public Boolean branchValidity() {
        final String branchNumber = branchNumber();
        final String branchVersion = branchVersion();
        return (branchVersion == null && branchNumber == null) ||
                (branchVersion != null && branchNumber != null);
    }

    /**
     * Whether the branch number of this VersionTreeId is valid.
     *
     * @return Whether the branch number of this VersionTreeId is valid.
     */
    public Boolean isBranchValidity() {
        return isBranch() ||  branchNumber() == null;
    }

    /**
     * Whether the branch version of this VersionTreeId is valid.
     *
     * @return Whether the branch version of this VersionTreeId is valid.
     */
    public Boolean isFirstValidity() {
        return trunkVersion().equals("1");
    }

    @JsonProperty(value = "_type")
    public String getTypeId() {
        return "VERSION_TREE_ID";
    }

}
