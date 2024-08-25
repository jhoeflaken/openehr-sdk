package nl.athena.openehr.base.foundation_types.interval;

public class Cardinality {

    private boolean isOrdered;
    private boolean isUnique;

    public boolean isBag() {
        return !isOrdered && !isUnique;
    }

    public boolean isSet() {
        return !isOrdered && isUnique;
    }

    public boolean isList() {
        return isOrdered && !isUnique;
    }

}
