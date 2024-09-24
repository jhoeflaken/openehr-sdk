package nl.athena.openehr.base.foundation_types.time;

/**
 * Temporal abstraction, representing a point in time. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_temporal_class">
 *     Temporal</a> class.
 */
public abstract class Temporal implements Comparable<Temporal> {

    private Double millis;

    public Double getMillis() {
        return millis;
    }

    @Override
    public int compareTo(final Temporal theOther) {
        return millis.compareTo(theOther.getMillis());
    }

    public boolean lessThan(final Iso8601Type theOther) {
        return millis < theOther.getMillis();
    }

    public boolean greaterThan(final Iso8601Type theOther) {
        return millis > theOther.getMillis();
    }

    public boolean lessOrEqual(final Iso8601Type theOther) {
        return millis <= theOther.getMillis();
    }

    public boolean greaterOrEqual(final Iso8601Type theOther) {
        return millis >= theOther.getMillis();
    }

    public boolean equals(final Iso8601Type theOther) {
        return millis == theOther.getMillis();
    }

}
