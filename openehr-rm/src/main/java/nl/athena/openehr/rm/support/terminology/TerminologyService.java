package nl.athena.openehr.rm.support.terminology;

import java.util.List;
import java.util.Map;

public interface TerminologyService extends OpenEhrCodeSetIdentifiers, OpenEhrTerminologyGroupIdentifiers {

    TerminologyAccess terminology(String name);
    CodeSetAccess codeSet(String name);
    CodeSetAccess codeSetForId(String id);
    boolean hasTerminology(String name);
    boolean hasCodeSet(String name);
    List<String> terminologyIdentifiers();
    Map<String, String> openEhrCodeSets();
    List<String> codeSetIdentifiers();

}
