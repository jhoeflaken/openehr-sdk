package nl.athena.openehr.base.base_types.definitions;

/**
 * The OpenEHR basic definitions. Defines globally used constant values. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_basic_definitions_class">
 *     BASIC_DEFINITIONS</a> class
 */
public interface BasicDefinitions {

    /**
     * The CR character.
     */
    char CR = '\015';

    /**
     * The LF character.
     */
    char LF = '\012';

    /**
     * The name of the OpenEHR Any type.
     */
    String ANY_TYPE_NAME = "Any";

    /**
     * Regular expression pattern for any string.
     */
    String REGEX_ANY_PATTERN = ".*";

    /**
     * The default encoding, which is UTF-8.
     */
    String DEFAULT_ENCODING = "UTF-8";

    /**
     * The name of the OpenEHR None type.
     */
    String NONE_TYPE_NAME = "None";

}
