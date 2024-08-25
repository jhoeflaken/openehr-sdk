package nl.athena.openehr.rm.ehr.data_types.quantity;

import nl.athena.openehr.rm.ehr.data_types.text.DvCodedText;

public class DvOrdinal extends DvOrdered<Integer> {

    private DvCodedText symbol;
    private Integer value;

    @Override
    public int compareTo(final Integer theOther) {
        return value.compareTo(theOther);
    }

}
