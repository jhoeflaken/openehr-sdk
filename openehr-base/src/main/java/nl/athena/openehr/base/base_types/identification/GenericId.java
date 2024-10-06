package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

@Getter
@SuperBuilder
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GENERIC_ID")
public class GenericId extends ObjectId {

    @JsonProperty(value = "scheme", required = true)
    @XmlElement(name = "scheme", required = true)
    private final String scheme;

    /**
     * Constructs a GenericId with the given value and scheme.
     *
     * @param theValue The value of the GenericId.
     * @param theScheme The scheme of the GenericId.
     */
    public GenericId(@NotNull final String theValue,
                     @NotNull final String theScheme) {
        super(theValue);
        scheme = theScheme;
    }

    @Override
    public String getType() {
        return "GENERIC_ID";
    }

}
