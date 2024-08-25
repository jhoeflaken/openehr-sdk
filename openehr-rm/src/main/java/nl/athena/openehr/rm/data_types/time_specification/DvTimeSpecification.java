package nl.athena.openehr.rm.data_types.time_specification;

import nl.athena.openehr.rm.data_types.DataValue;
import nl.athena.openehr.rm.data_types.encapsulated.DvParsable;

public abstract class DvTimeSpecification implements DataValue {

    private DvParsable value;

    abstract public String calendarAlignment();

    abstract public String eventAlignment();

    abstract public String institutionSpecified();

}
