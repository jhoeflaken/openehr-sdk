package nl.athena.openehr.base.foundation_types.terminology;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.net.URI;
import java.util.regex.Pattern;

/**
 * Primitive type representing a standalone reference to a terminology concept, in the form of a terminology
 * identifier, optional version, and a code or code string from the terminology. See
 * <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_terminology_code_class">
 * TerminologyCode</a> class.
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TERMINOLOGY_CODE")
public class TerminologyCode {

    private static final Pattern pattern = Pattern.compile(
            "\\[(?<terminologyId>[^()]+)(\\((?<terminologyVersion>.+)\\))?::(?<codeString>.+)]");

    @JsonProperty(value = "terminology_id", required = true)
    @XmlElement(name = "terminology_id", required = true)
    private String terminologyId;

    @JsonProperty(value = "terminology_version")
    @XmlElement(name = "terminology_version")
    @Nullable
    private String terminologyVersion;

    @JsonProperty(value = "code_string", required = true)
    @XmlElement(name = "code_string", required = true)
    private String codeString;

    @JsonProperty(value = "uri")
    @XmlElement(name = "uri")
    private URI uri;

    /**
     * Create a new terminology code from a string.
     *
     * @param theValue The string value.
     * @return The terminology code.
     */
    @JsonCreator
    public static TerminologyCode of(final String theValue) {
        // Check if the value is null.
        if (theValue == null) {
            return null;
        }

        final TerminologyCodeBuilder<?, ?> builder = TerminologyCode.builder();
        final var matcher = pattern.matcher(theValue);
        if (!matcher.matches()) {
            builder.withCodeString(theValue);
        } else {
            builder.withTerminologyId(matcher.group("terminologyId"));
            builder.withTerminologyVersion(matcher.group("terminologyVersion"));
            builder.withCodeString(matcher.group("codeString"));
        }

        return builder().build();
    }

    /**
     * Create a new terminology code.
     *
     * @param theTerminologyId      The terminology identifier.
     * @param theTerminologyVersion The terminology version.
     * @param theCodeString         The code string.
     * @return The terminology code.
     */
    @JsonCreator
    public static TerminologyCode of(
            @JsonProperty(value = "terminology_id") final String theTerminologyId,
            @JsonProperty(value = "terminology_version") final String theTerminologyVersion,
            @JsonProperty(value = "code_string") final String theCodeString) {
        return TerminologyCode.builder()
                .withTerminologyId(theTerminologyId)
                .withTerminologyVersion(theTerminologyVersion)
                .withCodeString(theCodeString)
                .build();
    }

    /**
     * Create a new terminology code.
     *
     * @param theTerminologyId The terminology identifier.
     * @param theCodeString    The code string.
     */
    public TerminologyCode(
            @Nonnull final String theTerminologyId,
            @Nonnull final String theCodeString) {
        terminologyId = theTerminologyId;
        codeString = theCodeString;
    }

    @JsonProperty(value = "_type")
    public String getType() {
        return "TERMINOLOGY_CODE";
    }

    @Override
    public String toString() {
        return terminologyVersion == null ?
                "[" + getTerminologyId() + "::" + getCodeString() + "]" :
                "[" + getTerminologyId() + "(" + terminologyVersion + ")::" + getCodeString() + "]";
    }

}
