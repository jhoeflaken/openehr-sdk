package nl.athena.openehr.base.foundation_types.interval;

import jakarta.validation.constraints.NotNull;

/**
 * Express constraints on the cardinality of container objects which are the values of multiply-valued attributes,
 * including uniqueness and ordering, providing the means to state that a container acts like a logical list, set or
 * bag. See <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_cardinality_class">
 *     Cardinality</a> class.
 */
public class Cardinality {

    private final MultiplicityInterval interval;
    private final boolean isOrdered;
    private final boolean isUnique;

    /**
     * Create a Cardinality object representing a bag.
     *
     * @param interval The interval of the cardinality.
     * @return A Cardinality object representing a bag.
     */
    public static Cardinality ofBag(final MultiplicityInterval interval) {
        return new Cardinality(interval, false, false);
    }

    /**
     * Create a Cardinality object representing a set.
     *
     * @param interval The interval of the cardinality.
     * @return A Cardinality object representing a set.
     */
    public static Cardinality ofSet(final MultiplicityInterval interval) {
        return new Cardinality(interval, false, true);
    }

    /**
     * Create a Cardinality object representing a list.
     *
     * @param interval The interval of the cardinality.
     * @return A Cardinality object representing a list.
     */
    public static Cardinality ofList(final MultiplicityInterval interval) {
        return new Cardinality(interval, true, false);
    }

    /**
     * Constructor for Cardinality.
     *
     * @param theInterval The interval of the cardinality.
     * @param theIsOrdered True if the cardinality is ordered.
     * @param theIsUnique True if the cardinality is unique.
     */
    public Cardinality(
            @NotNull final MultiplicityInterval theInterval,
            final boolean theIsOrdered,
            final boolean theIsUnique) {
        interval = theInterval;
        isOrdered = theIsOrdered;
        isUnique = theIsUnique;
    }

    /**
     * Get the interval specifying the cardinality.
     *
     * @return The interval specifying the cardinality.
     */
    public MultiplicityInterval getInterval() {
        return interval;
    }

    /**
     * True if the semantics of this cardinality represent a bag, i.e. unordered, non-unique membership.
     *
     * @return True if the cardinality represents a bag.
     */
    public boolean isBag() {
        return !isOrdered && !isUnique;
    }

    /**
     * True if the semantics of this cardinality represent a set, i.e. unordered, unique membership.
     *
     * @return True if the cardinality represents a set.
     */
    public boolean isSet() {
        return !isOrdered && isUnique;
    }

    /**
     * True if the semantics of this cardinality represent a list, i.e. ordered, non-unique membership.
     *
     * @return True if the cardinality represents a list.
     */
    public boolean isList() {
        return isOrdered && !isUnique;
    }

}
