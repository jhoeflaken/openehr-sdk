package nl.athena.openehr.base.foundation_types.time;

public class Iso8601Duration extends Iso8601Type {

    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601Duration(String theValue) {
        super(theValue);
    }

    public Integer years() {
        return 0;
    }

    public Integer months() {
        return 0;
    }

    public Integer days() {
        return 0;
    }

    public Integer hours() {
        return 0;
    }

    public Integer minutes() {
        return 0;
    }

    public Integer seconds() {
        return 0;
    }

    public Float fractionalSeconds() {
        return 0.0f;
    }

    public Integer weeks() {
        return 0;
    }

    public boolean isDecimalSignComma() {
        return false;
    }

    public Float toSeconds() {
        return 0.0f;
    }

    public String asString() {
        return null;
    }

    public Iso8601Duration add(final Iso8601Duration theOther) {
        return null;
    }

    public Iso8601Duration subtract(final Iso8601Duration theOther) {
        return null;
    }

    public Iso8601Duration multiply(final Float theFactor) {
        return null;
    }

    public Iso8601Duration divide(final Float theDivisor) {
        return null;
    }

    public Iso8601Duration negative() {
        return null;
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
