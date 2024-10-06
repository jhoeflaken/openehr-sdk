package nl.athena.openehr.base.foundation_types.terminology;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Leaf type representing a standalone term from a terminology, which consists of the term text and the code, i.e. a
 * concept reference. See <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_terminology_term_class">
 *     TerminologyTerm</a> class.
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TERMINOLOGY_TERM")
public class TerminologyTerm {

    @JsonProperty(value = "concept", required = true)
    private TerminologyCode concept;

    @JsonProperty(value = "text", required = true)
    private String text;

    @JsonProperty(value = "_type")
    public String getType() {
        return "TERMINOLOGY_TERM";
    }

    /**
     * Create a new terminology term.
     *
     * @param theConcept The concept reference.
     * @param theText The term text.
     */
    public TerminologyTerm(
            @Nonnull final TerminologyCode theConcept,
            @Nonnull final String theText) {
        concept = theConcept;
        text = theText;
    }

}
