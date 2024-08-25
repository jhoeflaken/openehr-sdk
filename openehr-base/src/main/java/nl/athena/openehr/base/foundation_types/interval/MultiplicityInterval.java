package nl.athena.openehr.base.foundation_types.interval;

public class MultiplicityInterval extends ProperInterval<Integer> {

    public static final String MULTIPLICITY_RANGE_MARKER = "..";
    public static final String MULTIPLICITY_UNBOUNDED_MARKER = "*";

    public boolean isOpen() {
        return lowerUnbounded || upperUnbounded;
    }

    public boolean isOptional() {
        return lower == 0 && lowerIncluded && upper == 1 && upperIncluded;
    }

    public boolean isMandatory() {
        return lower == 1 && lowerIncluded && upper == 1 && upperIncluded;
    }

    public boolean isProhibited() {
        return lower == 0 && lowerIncluded && upper == 0 && upperIncluded;
    }

}
