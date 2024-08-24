package nl.athena.openehr.base.base_types.identification;

import org.apache.commons.lang3.StringUtils;

public class VersionTreeId {

    private final String value;

    public VersionTreeId(final String theValue) {
        value = theValue;
    }

    public String trunkVersion() {
        final String[] parts = value.split("\\.");
        return parts[0];
    }

    public String branchVersion() {
        final String[] parts = value.split("\\.");
        if (parts.length > 1) {
            return parts[1];
        }

        return null;
    }

    public String branchNumber() {
        final String[] parts = value.split("\\.");
        if (parts.length > 2) {
            return parts[2];
        }

        return value;
    }

    public Boolean isBranch() {
        return StringUtils.isNotBlank(branchNumber()) && StringUtils.isNotBlank(branchVersion());
    }

    public Boolean valueValid() {
        return StringUtils.isNotBlank(value);
    }

    public Boolean TrunkVersionValid() {
        final String trunkVersion = trunkVersion();
        return StringUtils.isNotBlank(trunkVersion) && (Integer.parseInt(trunkVersion) >= 1);
    }

    public Boolean BranchVersionValid() {
        final String branchVersion = branchVersion();
        return StringUtils.isNotBlank(branchVersion) && (Integer.parseInt(branchVersion) >= 1);
    }

    public Boolean branchValidity() {
        final String branchNumber = branchNumber();
        final String branchVersion = branchVersion();
        return (branchVersion == null && branchNumber == null) ||
                (branchVersion != null && branchNumber != null);
    }

    public Boolean isBranchValidity() {
        return isBranch() ||  branchNumber() == null;
    }

    public Boolean isFirstValidity() {
        return trunkVersion().equals("1");
    }

}
