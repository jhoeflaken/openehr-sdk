package nl.athena.openehr.base.foundation_types.time;

/**
 * Temporal abstraction, representing a point in time. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_temporal_class">
 *     Temporal</a> class.
 */
public abstract class Temporal implements Comparable<Temporal> {

    /**
     * Arithmetic value comparison. Returns True if current object is less than the other. This operator is effected
     * and/or redefined in descendants to provide the appropriate ordering semantics for concrete types. In conjunction
     * with =, enables the definition of the related functions greater_than() etc.
     *
     * @param theOther The other Temporal object to compare with.
     * @return True if current object is less than the other.
     */
    public boolean lessThan(final Temporal theOther) {
        return compareTo(theOther) < 0;
    }

    /**
     * True if current object greater than the other.
     *
     * @param theOther The other Temporal object to compare with.
     * @return True if current object greater than the other.
     */
    public boolean greaterThan(final Temporal theOther) {
        return compareTo(theOther) > 0;
    }

    /**
     * True if current object less than or equal to the other.
     *
     * @param theOther The other Temporal object to compare with.
     * @return True if current object less than or equal to the other.
     */
    public boolean lessOrEqual(final Temporal theOther) {
        return compareTo(theOther) <= 0;
    }

    /**
     * True if current object greater than or equal to the other.
     *
     * @param theOther The other Temporal object to compare with.
     * @return True if current object greater than or equal to the other.
     */
    public boolean greaterOrEqual(final Temporal theOther) {
        return compareTo(theOther) >= 0;
    }

    /**
     * True if current object equal to the other.
     *
     * @param theOther The other Temporal object to compare with.
     * @return True if current object equal to the other.
     */
    @Override
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }

        if (theOther == null || getClass() != theOther.getClass()) {
            return false;
        }

        return compareTo((Temporal) theOther) == 0;
    }

}
