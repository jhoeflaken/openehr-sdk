package nl.athena.openehr.base.foundation_types.terminology;

import java.net.URI;

/**
 *
 * Primitive type representing a standalone reference to a terminology concept, in the form of a terminology
 * identifier, optional version, and a code or code string from the terminology. See
 * <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_terminology_code_class">
 *     TerminologyCode</a> class.
 */
public class TerminologyCode {

    private String terminologyId;
    private String terminologyVersion;
    private String codeString;
    private URI uri;

}
