package nl.athena.openehr.rm.data_types.basic;

import nl.athena.openehr.rm.data_types.DataValue;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

public class DvState implements DataValue {

    private DvCodedText value;
    private Boolean isTerminal;

}
