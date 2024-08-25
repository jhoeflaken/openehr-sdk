package nl.athena.openehr.rm.common.generic;

import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.text.DvCodedText;
import nl.athena.openehr.rm.data_types.text.DvText;

public class AuditDetails {

    private String systemId;
    private DvDateTime timeCommitted;
    private DvCodedText changeType;
    private DvText description;
    private PartyProxy committer;

}
