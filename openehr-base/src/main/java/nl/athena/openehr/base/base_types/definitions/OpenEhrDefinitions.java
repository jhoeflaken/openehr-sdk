package nl.athena.openehr.base.base_types.definitions;

/**
 * The OpenEHR definitions, which includes the basic definitions. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_openehr_definitions_class">
 *     OPENEHR_DEFINITIONS</a>
 */
public interface OpenEhrDefinitions extends BasicDefinitions {

    /**
     * Predefined terminology identifier to indicate it is local to the knowledge resource in which it occurs, e.g.
     * an archetype
     */
    String LOCAL_TERMINOLOGY_ID = "local";

}
