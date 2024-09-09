package nl.athena.openehr.rm.support.terminology;

public interface OpenEhrCodeSetIdentifiers {

    String CODE_SET_ID_CHARACTER_SETS = "character_sets";
    String CODE_SET_ID_COMPRESSION_ALGORITHMS = "compression_algorithms";
    String CODE_SET_ID_COUNTRIES = "countries";
    String CODE_SET_INTEGRITY_CHECK_ALGORITHMS = "integrity_check_algorithms";
    String CODE_SET_ID_LANGUAGES = "languages";
    String CODE_SET_ID_MEDIA_TYPES = "media_types";
    String CODE_SET_ID_NORMAL_STATUS = "normal_status";

    boolean validCodeSetId(String codeSetId);

}
