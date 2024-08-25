package nl.athena.openehr.rm.common.archetyped;

import nl.athena.openehr.base.base_types.identification.UidBasedId;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public abstract class Locatable implements Pathable {

    protected DvText name;
    protected String archetypeNodeId;
    protected Archetyped archetypeDetails;
    protected FeederAudit feederAudit;
    protected List<Link> links;
    protected UidBasedId uid;

    public DvText concept() {
        return null;
    }

    public boolean isArchetypeRoot() {
        return false;
    }

}
