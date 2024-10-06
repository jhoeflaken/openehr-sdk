package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * Identifier for parties in a demographic or identity service. There are typically a number of subtypes of the PARTY
 * class, including PERSON, ORGANISATION, etc. Abstract supertypes are allowed if the referenced object is of a type
 * not known by the current implementation of this class (in other words, if the demographic model is changed by the
 * addition of a new PARTY or ACTOR subtypes, valid PARTY_REFs can still be constructed to them).
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_party_ref_class">PartyRef</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LOCATABLE_REF")
public class LocatableRef extends ObjectRef {

    @JsonIgnore
    @XmlTransient
    private String path;

    @JsonIgnore
    @XmlTransient
    private UidBasedId id;

    /**
     * Creates a new LocatableRef with the given values.
     *
     * @param theNamespace The namespace.
     * @param theType The type of object being referenced.
     * @param theId The id of the object being referenced.
     */
    public LocatableRef(String theNamespace, String theType, ObjectId theId) {
        super(theNamespace, theType, theId);
    }

    /**
     * Creates a new LocatableRef with the given values.
     *
     * @return The path as URI.
     */
    public String asUri() {
        final int index = namespace.indexOf(":");
        final String scheme = namespace.substring(0, index);
        final String uri = scheme + ":" + id.value;

        return StringUtils.isNotBlank(path) ? uri + "/" + path : uri;
    }

    @JsonProperty(value = "_type")
    public String getType() {
        return "LOCATABLE_REF";
    }

}
