package nl.athena.openehr.rm.ehr.data_types.quantity;

import jakarta.validation.constraints.NotNull;

public interface ProportionKind {

    int PK_RATIO = 0;
    int PK_UNITARY = 1;
    int PK_PERCENT = 2;
    int PK_FRACTION = 3;
    int PK_INTEGER_FRACTION = 4;

    boolean validProportionKind(@NotNull final Integer theValue);

}
