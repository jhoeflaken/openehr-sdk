package nl.athena.openehr.base.foundation_types.time;

public class Iso8601DateTime extends Iso8601Type {
    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601DateTime(String theValue) {
        super(theValue);
    }

    @Override
    public boolean isPartial() {
        return false;
    }

    @Override
    public boolean isExtended() {
        return false;
    }
}
