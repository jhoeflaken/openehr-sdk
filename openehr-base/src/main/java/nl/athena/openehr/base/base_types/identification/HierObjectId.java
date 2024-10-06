package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Concrete type corresponding to hierarchical identifiers of the form defined by UID_BASED_ID.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_hier_object_id_class">HierObjectId</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HIER_OBJECT_ID")
public class HierObjectId extends UidBasedId {

    /**
     * Creates a new {@link HierObjectId} with a random value.
     *
     * @param theValue The value.
     */
    public HierObjectId(@Nonnull final  String theValue) {
        super(theValue);
    }

    @Override
    @JsonProperty(value = "_type")
    public String getType() {
        return "HIER_OBJECT_ID";
    }

}
