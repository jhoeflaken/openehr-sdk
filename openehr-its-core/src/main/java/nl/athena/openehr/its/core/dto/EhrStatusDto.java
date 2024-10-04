package nl.athena.openehr.its.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.annotation.Nullable;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import nl.athena.openehr.base.base_types.identification.UidBasedId;
import nl.athena.openehr.rm.common.archetyped.Archetyped;
import nl.athena.openehr.rm.common.archetyped.FeederAudit;
import nl.athena.openehr.rm.common.generic.PartySelf;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.text.DvText;

@JsonRootName("EHR_STATUS")
@JacksonXmlRootElement(localName = "ehr_status")
@XmlType(name = "EHR_STATUS")
public record EhrStatusDto(
        @JsonProperty("uid")
        @XmlElement(name = "uid")
        @Nullable
        UidBasedId uid,

        @JsonProperty(value = "archetype_node_id", required = true)
        @XmlAttribute(name = "archetype_node_id", required = true)
        String archetypeNodeId,

        @JsonProperty(value = "name", required = true)
        @XmlElement(name = "name", required = true)
        DvText name,

        @JsonProperty(value = "archetype_details")
        @XmlElement(name = "archetype_details")
        @Nullable
        Archetyped archetypeDetails,

        @JsonProperty(value = "feeder_audit")
        @XmlElement(name = "feeder_audit")
        @Nullable
        FeederAudit feederAudit,

        @JsonProperty(value = "subject", required = true)
        @XmlElement(name = "subject", required = true)
        PartySelf subject,

        @JsonProperty(value = "is_queryable", required = true)
        @XmlElement(name = "is_queryable", required = true)
        Boolean isQueryable,

        @JsonProperty(value = "is_modifiable", required = true)
        @XmlElement(name = "is_modifiable", required = true)
        Boolean isModifiable,

        @JsonProperty(value = "other_details")
        @XmlElement(name = "other_details")
        @Nullable
        ItemStructure otherDetails) {

    @JsonProperty(value = "_type", required = true)
    @XmlElement(name = "_type", required = true)
    public String type() {
        return "EHR_STATUS";
    }

}
