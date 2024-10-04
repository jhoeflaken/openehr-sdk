package nl.athena.openehr.its.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import nl.athena.openehr.base.base_types.identification.HierObjectId;
import nl.athena.openehr.base.base_types.identification.ObjectRef;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

@JsonRootName("EHR")
@JacksonXmlRootElement(localName = "ehr")
@XmlType(name = "EHR")
public record EhrDto(
        @JsonProperty("system_id")
        @XmlElement(name = "systemId")
        HierObjectId systemId,

        @JsonProperty("ehr_id")
        @XmlElement(name = "ehrId")
        HierObjectId ehrId,

        @JsonProperty("ehr_status")
        @XmlElement(name = "ehrStatus")
        ObjectRef ehrStatus,

        @JsonProperty("ehr_access")
        @XmlElement(name = "ehrAccess")
        ObjectRef ehrAccess,

        @JsonProperty("time_created")
        @XmlElement(name = "timeCreated")
        DvDateTime timeCreated) {
}
