package nl.athena.openehr.rm.data_types.time_specification;

import nl.athena.openehr.rm.data_types.quantity.date_time.DvDuration;

public class DvPeriodicTimeSpecification extends DvTimeSpecification {

    @Override
    public String calendarAlignment() {
        return "";
    }

    @Override
    public String eventAlignment() {
        return "";
    }

    @Override
    public String institutionSpecified() {
        return "";
    }

    public DvDuration period() {
        return null;
    }

}
