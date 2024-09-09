package nl.athena.openehr.rm.support.terminology;

import nl.athena.openehr.rm.data_types.text.CodePhrase;

import java.util.List;

public interface TerminologyAccess {

    String id();
    CodePhrase allCodes();
    List<CodePhrase> codesForGroupId(String groupId);
    List<CodePhrase> codesForGroupName(String lang, String groupName);
    boolean hasCodeForGroupId();
    String rubricForCode(String code);

}
