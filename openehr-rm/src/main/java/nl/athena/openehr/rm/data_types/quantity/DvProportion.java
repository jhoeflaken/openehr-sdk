package nl.athena.openehr.rm.data_types.quantity;

import jakarta.validation.constraints.NotNull;

public class DvProportion extends DvAmount implements ProportionKind {

    private Float numerator;
    private Float denominator;
    private Integer type;
    private Integer precision;

    public Float magnitude() {
        return 0f;
    }

    public boolean isIntegral() {
        return false;
    }

    @Override
    public boolean validProportionKind(@NotNull final Integer theValue) {
        return false;
    }

}
