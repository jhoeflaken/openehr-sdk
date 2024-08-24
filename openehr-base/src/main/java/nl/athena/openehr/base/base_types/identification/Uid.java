package nl.athena.openehr.base.base_types.identification;

import jakarta.validation.constraints.NotNull;

public class Uid {

    private final String value;

    public Uid(@NotNull String theValue) {
        value = theValue;
    }

}
