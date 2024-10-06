package nl.athena.openehr.base.base_types.identification;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Identifier for archetypes. Ideally these would identify globally unique archetypes.
 * <br/><br/>
 * Lexical form: rm_originator '-' rm_name '-' rm_entity '.' concept_name { '-' specialisation }* '.v' number.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_archetype_id_class">ArchetypeId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TEMPLATE_ID")
public class TemplateId extends ObjectId {

    /**
     * Constructs a new TemplateId with the given value.
     *
     * @param theValue The value of the TemplateId.
     */
    public TemplateId(String theValue) {
        super(theValue);
    }

    @Override
    public String getType() {
        return "TEMPLATE_ID";
    }

}
