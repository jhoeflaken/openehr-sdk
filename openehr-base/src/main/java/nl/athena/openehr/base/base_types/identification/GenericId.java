package nl.athena.openehr.base.base_types.identification;

import jakarta.validation.constraints.NotNull;

public class GenericId extends ObjectId {

    private final String scheme;

    public GenericId(@NotNull final String theValue,
                     @NotNull final String theScheme) {
        super(theValue);
        scheme = theScheme;
    }

}
