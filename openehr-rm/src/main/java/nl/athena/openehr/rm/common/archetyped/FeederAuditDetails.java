package nl.athena.openehr.rm.common.archetyped;

import nl.athena.openehr.rm.common.generic.PartyIdentified;
import nl.athena.openehr.rm.common.generic.PartyProxy;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

public class FeederAuditDetails {

    private String systemId;
    private PartyIdentified location;
    private PartyProxy subject;
    private PartyIdentified provider;
    private DvDateTime time;
    private String versionId;
    private ItemStructure otherDetails;

}
