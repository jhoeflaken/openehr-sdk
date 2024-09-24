package nl.athena.openehr.base.base_types.builtins;

import nl.athena.openehr.base.foundation_types.terminology.TerminologyCode;

/**
 * Class representing the locale. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_locale_interface">
 *     Locale</a> interface.
 */
public interface Locale {

    TerminologyCode primaryLanguage();

}
