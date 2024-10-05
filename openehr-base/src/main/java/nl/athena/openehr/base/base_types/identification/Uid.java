package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 *
 * Abstract parent of classes representing unique identifiers which identify information entities in a durable way.
 * UIDs only ever identify one information entity in time or space and are never re-used.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_uid_class">Uid</a>
 */
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "UID")
public abstract class Uid {

    /**
     * The value of the UID.
     */
    @JsonProperty(value = "value", required = true)
    @XmlElement(name = "value", required = true)
    private final String value;

    /**
     * Constructs a UID with the given value.
     *
     * @param theValue The value of the UID.
     */
    protected Uid(@Nonnull String theValue) {
        value = theValue;
    }

    /**
     * Returns the type of this UID.
     *
     * @return the type of this UID
     */
    @JsonProperty(value = "_type")
    public abstract String getTypeId();

}
