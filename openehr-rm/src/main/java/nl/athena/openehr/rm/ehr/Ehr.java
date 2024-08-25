package nl.athena.openehr.rm.ehr;

import nl.athena.openehr.base.base_types.identification.HierObjectId;
import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

import java.util.List;

public class Ehr {

    private HierObjectId systemId;
    private HierObjectId ehrId;
    private DvDateTime timeCreated;
    private List<ObjectRef> contributions;
    private List<ObjectRef> compositions;
    private ObjectRef ehrStatus;
    private ObjectRef ehrAccess;
    private ObjectRef directory;
    private List<ObjectRef> folders;

}
