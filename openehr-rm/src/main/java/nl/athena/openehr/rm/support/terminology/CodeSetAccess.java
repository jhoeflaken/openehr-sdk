package nl.athena.openehr.rm.support.terminology;

import nl.athena.openehr.rm.data_types.text.CodePhrase;

import java.util.List;

public interface CodeSetAccess {

    String id();
    List<CodePhrase> allCodes();
    boolean hasLang(String lang);
    boolean hasCode(String code);

}
