package nl.athena.openehr.its.rest.dto.ehr;

import com.fasterxml.jackson.annotation.JsonProperty;
import nl.athena.openehr.base.base_types.identification.HierObjectId;
import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

public class Ehr {

    @JsonProperty(value = "system_id")
    private HierObjectId systemId;

    @JsonProperty(value = "ehr_id")
    private HierObjectId ehrId;

    @JsonProperty(value = "ehr_status")
    private ObjectRef ehrStatus;

    @JsonProperty(value = "ehr_access")
    private ObjectRef ehrAccess;

    @JsonProperty(value = "time_created")
    private DvDateTime timeCreated;

}
