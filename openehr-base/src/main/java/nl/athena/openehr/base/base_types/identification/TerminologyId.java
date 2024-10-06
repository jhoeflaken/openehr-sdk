package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Identifier for terminologies such as accessed via a terminology query service. In this class, the value attribute
 * identifies the Terminology in the terminology service, e.g. SNOMED-CT . A terminology is assumed to be in a
 * particular language, which must be explicitly specified.
 * <br/><br/>
 * The value if the id attribute is the precise terminology id identifier, including actual release (i.e. actual
 * version), local modifications etc; e.g. ICPC2.
 * <br/><br/>
 * Lexical form: name [ '(' version ')' ].
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_terminology_id_class">TerminologyId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@Jacksonized
@XmlType(name = "TERMINOLOGY_ID")
public class TerminologyId extends ObjectId {

    @JsonIgnore
    @XmlTransient
    private final String name;

    @JsonIgnore
    @XmlTransient
    private final String version;

    /**
     * Constructs a new TerminologyId with the given value.
     *
     * @param theValue The value of the TerminologyId.
     */
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

    @Override
    public String getType() {
        return "TERMINOLOGY_ID";
    }

    /**
     * Returns the name part of the TerminologyId.
     *
     * @return the name part of the TerminologyId.
     */
    public String name() {
        return name;
    }

    /**
     * Returns the version part of the TerminologyId.
     *
     * @return the version part of the TerminologyId.
     */
    public String versionId() {
        return version;
    }

}
