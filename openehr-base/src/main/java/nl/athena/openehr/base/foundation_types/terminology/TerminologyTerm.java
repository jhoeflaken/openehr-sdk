package nl.athena.openehr.base.foundation_types.terminology;

/**
 * Leaf type representing a standalone term from a terminology, which consists of the term text and the code, i.e. a
 * concept reference. See <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_terminology_term_class">
 *     TerminologyTerm</a> class.
 */
public class TerminologyTerm {

    private TerminologyCode concept;
    private String text;

}
