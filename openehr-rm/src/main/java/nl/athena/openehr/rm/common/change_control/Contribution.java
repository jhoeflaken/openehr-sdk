package nl.athena.openehr.rm.common.change_control;

import nl.athena.openehr.base.base_types.identification.HierObjectId;
import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.common.generic.AuditDetails;

import java.util.List;

public class Contribution {

    private HierObjectId uid;
    private AuditDetails audit;
    private List<ObjectRef> versions;


}
