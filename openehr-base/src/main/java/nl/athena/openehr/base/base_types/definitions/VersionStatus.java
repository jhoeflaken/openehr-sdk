package nl.athena.openehr.base.base_types.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Status of a versioned artefact, as one of a number of possible values: uncontrolled, prerelease, release, build.
 */
public enum VersionStatus {
    ALPHA("alpha", "{0}-alpha.{1}"),
    BETA("beta", " {0}-beta.{1}"),
    RELEASE_CANDIDATE("release_candidate", "{0}-rc.{1}"),
    RELEASED("released", "{0}"),
    BUILD("build", "{0}+{1}");

    private final String value;
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
     * Returns the official OpenEHR value of the enumeration.
     *
     * @return The official OpenEHR value of the enumeration.
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Returns the template for the version status.
     *
     * @return The template for the version status.
     */
    public String getTemplate() {
        return template;
    }

    /**
     * Creates a {@link VersionStatus} from the given value string.
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
