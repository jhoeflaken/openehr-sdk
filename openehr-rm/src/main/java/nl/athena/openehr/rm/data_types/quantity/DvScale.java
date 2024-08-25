package nl.athena.openehr.rm.data_types.quantity;

import nl.athena.openehr.rm.data_types.text.DvCodedText;

public class DvScale extends DvOrdered<Float> {

    private DvCodedText symbol;
    private Float value;

    @Override
    public int compareTo(final Float theOther) {
        return theOther.compareTo(value);
    }

}
