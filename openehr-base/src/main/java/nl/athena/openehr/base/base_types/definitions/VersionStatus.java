package nl.athena.openehr.base.base_types.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;

/**
 * Status of a versioned artefact, as one of a number of possible values: uncontrolled, prerelease, release, build.
 * See <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_version_status_enumeration">
 *     VERSION_STATUS</a> enumeration.
 */
@XmlType(name = "VERSION_STATUS")
@XmlEnum
@Getter
public enum VersionStatus {
    /**
     *
     * Value representing a version which is 'unstable', i.e. contains an unknown size of change with respect to its
     * base version. Rendered with the build number as a string in the form N.M.P-alpha.B e.g. 2.0.1-alpha.154.
     * <p />
     * The {0} is the version number in major.minor.patch format, and {1} is the build number.
     */
    @XmlEnumValue("alpha")
    ALPHA("alpha", "{0}-alpha.{1}"),

    /**
     * Value representing a version which is 'beta', i.e. contains an unknown but reducing size of change with respect
     * to its base version. Rendered with the build number as a string in the form N.M.P-beta.B e.g. 2.0.1-beta.154.
     * <p />
     * The {0} is the version number in major.minor.patch format, and {1} is the build number.
     */
    @XmlEnumValue("beta")
    BETA("beta", " {0}-beta.{1}"),

    /**
     * Value representing a version which is 'release candidate', i.e. contains only patch-level changes on the base
     * version. Rendered as a string as N.M.P-rc.B e.g. 2.0.1-rc.27.
     * <p />
     * The {0} is the version number in major.minor.patch format, and {1} is the build number.
     */
    @XmlEnumValue("release_candidate")
    RELEASE_CANDIDATE("release_candidate", "{0}-rc.{1}"),

    /**
     * Value representing a version which is 'released', i.e. is the definitive base version. N.M.P e.g. 2.0.1.
     * <p />
     * The {0} is the version number in major.minor.patch format.
     */
    @XmlEnumValue("released")
    RELEASED("released", "{0}"),

    /**
     * Value representing a version which is a build of the current base release. Rendered with the build number as a
     * string in the form N.M.P+B e.g. 2.0.1+33.
     * <p />
     * The {0} is the version number in major.minor.patch format, and {1} is the build number.
     */
    @XmlEnumValue("build")
    BUILD("build", "{0}+{1}");

    @JsonValue
    private final String value;

    @XmlTransient
    @JsonIgnore
    private final String template;

    /**
     * Constructor.
     *
     * @param theValue The official OpenEHR value of the enumeration.
     * @param theTemplate The template for the version status. {0} is the version number in major.minor.patch format,
     *                    and {1} is the build number.
     */
    VersionStatus(final String theValue,
                  final String theTemplate) {
        value = theValue;
        template = theTemplate;
    }

    @Override
    public String toString() {
        return value;
    }

    /**
     * Creates a {@link VersionStatus} from the given value string. This method is used by Jackson during JSON
     * deserialization.
     *
     * @param theValue The OpenEHR version status string value.
     * @return The {@link VersionStatus} for the specified value.
     */
    @JsonCreator
    VersionStatus fromValue(final String theValue) {
        for (VersionStatus versionStatus : VersionStatus.values()) {
            if (versionStatus.value.equalsIgnoreCase(theValue)) {
                return versionStatus;
            }
        }

        return null;
    }

}
