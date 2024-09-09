package nl.athena.openehr.rm.composition;

import nl.athena.openehr.rm.common.generic.Participation;
import nl.athena.openehr.rm.common.generic.PartyIdentified;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

import java.util.List;

public class EventContext {

    private DvDateTime startTime;
    private DvDateTime endTime;
    private String location;
    private DvCodedText setting;
    private ItemStructure otherContext;
    private List<Participation> participations;
    private PartyIdentified healthCareFacility;

}
