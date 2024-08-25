package nl.athena.openehr.rm.data_types.quantity;

public abstract class DvQuantified<T> extends DvOrdered<T> {

    protected String magnitudeStatus;
    protected T accuracy;

    public boolean validMagnitudeStatus() {
        return magnitudeStatus != null && !magnitudeStatus.isEmpty();
    }

    public T magnitude() {
        return null;
    }

    public boolean accuracyUnknown() {
        return accuracy == null;
    }

}
