package nl.athena.openehr.rm.common.generic;

import nl.athena.openehr.rm.data_types.quantity.DvInterval;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvTime;
import nl.athena.openehr.rm.data_types.text.DvCodedText;
import nl.athena.openehr.rm.data_types.text.DvText;

public class Participation {

    private DvText function;
    private DvCodedText mode;
    private DvInterval<DvTime> time;
    private PartyProxy performer;

}
