package nl.athena.openehr.rm.ehr.data_types.quantity;

import jakarta.validation.constraints.NotNull;

public class DvAmount extends DvQuantified<Float> {

    private boolean accuracyIsPercent;

    @Override
    public int compareTo(final Float theOther) {
        return accuracy.compareTo(theOther);
    }

    public boolean validPercentage(@NotNull final Number percentage) {
        return false;
    }

    public DvAmount add(@NotNull final DvAmount theOther) {
        return null;
    }

    public DvAmount subtract(@NotNull final DvAmount theOther) {
        return null;
    }

    public DvAmount multiply(@NotNull final DvAmount theOther) {
        return null;
    }

    public DvAmount negative() {
        return null;
    }

    public boolean lessThan(@NotNull final DvAmount theOther) {
        return false;
    }

}
