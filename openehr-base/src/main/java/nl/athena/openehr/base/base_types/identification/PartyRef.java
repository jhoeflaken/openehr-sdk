package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Identifier for parties in a demographic or identity service. There are typically a number of subtypes of the PARTY
 * class, including PERSON, ORGANISATION, etc. Abstract supertypes are allowed if the referenced object is of a type
 * not known by the current implementation of this class (in other words, if the demographic model is changed by the
 * addition of a new PARTY or ACTOR subtypes, valid PARTY_REFs can still be constructed to them).
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_party_ref_class">PartyRef</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PARTY_REF")
public class PartyRef extends ObjectRef {

    /**
     * Check if the type is valid.
     *
     * @return True if the type is valid.
     */
    public Boolean isValidType() {
        return type.equals("PARTY") ||
                type.equals("ORGANISATION") ||
                type.equals("PERSON") ||
                type.equals("GROUP") ||
                type.equals("AGENT") ||
                type.equals("ROLE") ||
                type.equals("ACTOR");
    }

    @JsonProperty("_type")
    public String getType() {
        return "PARTY_REF";
    }

}
