package nl.athena.openehr.base.foundation_types.functional;

import lombok.Getter;

@Getter
public class Tuple1<A> extends Tuple {

    public final A value;

    /**
     * Creates a new Tuple1 with the given value.
     *
     * @param theValue The value
     */
    public Tuple1(final A theValue) {
        value = theValue;
    }

}
