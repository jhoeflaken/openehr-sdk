package nl.athena.openehr.rm.common.change_control;

import nl.athena.openehr.base.base_types.identification.HierObjectId;
import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

public class VersionedObject<T> {

    private HierObjectId uid;
    private ObjectRef ownerId;
    private DvDateTime timeCreated;

}
