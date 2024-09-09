package nl.athena.openehr.rm.support.terminology;

public interface OpenEhrTerminologyGroupIdentifiers {

    String TERMINOLOGY_ID_OPENEHR = "openehr";
    String GROUP_ID_AUDIT_CHANGE_TYPE = "audit_change_type";
    String GROUP_ID_ATTESTATION_REASON = "attestation_reason";
    String GROUP_ID_COMPOSITION_CATEGORY = "composition_category";
    String GROUP_ID_EVENT_MATH_FUNCTION = "event_math_function";
    String GROUP_ID_INSTRUCTION_STATES = "instruction_states";
    String GROUP_ID_INSTRUCTION_TRANSITIONS = "instruction_transitions";
    String GROUP_ID_NULL_FLAVOURS = "null_flavours";
    String GROUP_ID_PROPERTY = "property";
    String GROUP_ID_PARTICIPATION_FUNCTION = "participation_function";
    String GROUP_ID_PARTICIPATION_MODE = "participation_mode";
    String GROUP_ID_SETTING = "setting";
    String GROUP_ID_TERM_MAPPING_PURPOSE = "term_mapping_purpose";
    String GROUP_ID_SUBJECT_RELATIONSHIP = "subject_relationship";
    String GROUP_ID_VERSION_LIFECYCLE_STATE = "version_lifecycle_state";

    boolean validTerminologyGroupId(String terminologyGroupId);

}
