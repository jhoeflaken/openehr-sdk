package nl.athena.openehr.base.foundation_types.time;

/**
 * Abstract ancestor type of ISO 8601 types, defining interface for 'extended' and 'partial' concepts from ISO 8601.
 * See <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_iso8601type_class">
 *     Iso8601Type</a> class.
 */
public abstract class Iso8601Type extends Temporal implements TimeDefinitions {

    private final String value;

    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601Type(final String theValue) {
        value = theValue;
    }

    /**
     * Get the ISO8601 value.
     *
     * @return The ISO8601 value.
     */
    public String getValue() {
        return value;
    }

    /**
     * True if this date time is partial, i.e. if trailing end (right hand) value(s) is/are missing.
     *
     * @return True if this date time is partial.
     */
    abstract public boolean isPartial();

    /**
     * True if this ISO8601 string is in the 'extended' form, i.e. uses '-' and / or ':' separators. This is the
     * preferred format.
     *
     * @return True if this ISO8601 string is in the 'extended' form.
     */
    abstract public boolean isExtended();

}
