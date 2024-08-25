package nl.athena.openehr.rm.data_types.quantity;

import jakarta.validation.constraints.NotNull;

public abstract class DvAbsoluteQuantity<T extends DvAmount> extends DvQuantified<T> {

    public DvAbsoluteQuantity<T> add(@NotNull final  DvAmount theAmount) {
        return null;
    }

    public DvAbsoluteQuantity<T> subtract(@NotNull final DvAmount theAmount) {
        return null;
    }

    public DvAmount diff(@NotNull final DvAbsoluteQuantity<T> theOther) {
        return null;
    }

}
