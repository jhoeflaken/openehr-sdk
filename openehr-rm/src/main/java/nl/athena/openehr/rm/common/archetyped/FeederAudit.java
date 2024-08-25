package nl.athena.openehr.rm.common.archetyped;

import nl.athena.openehr.rm.data_types.basic.DvIdentifier;
import nl.athena.openehr.rm.data_types.encapsulated.DvEncapsulated;

import java.util.List;

public class FeederAudit {

    private List<DvIdentifier> originatingSystemItemIds;
    private List<DvIdentifier> feederSystemItemIds;
    private DvEncapsulated originalContent;
    private FeederAuditDetails originatingSystemAudit;
    private FeederAuditDetails feederSystemAudit;

}
