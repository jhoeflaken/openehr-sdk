package nl.athena.openehr.base.foundation_types.functional;

import lombok.Getter;

@Getter
public class Tuple2<A, B> extends Tuple {

    private final A value1;
    private final B value2;

    /**
     * Creates a new Tuple2 with the given values.
     *
     * @param theValue1 The first value
     * @param theValue2 The second value
     */
    public Tuple2(final A theValue1, final B theValue2) {
        value1 = theValue1;
        value2 = theValue2;
    }

}
