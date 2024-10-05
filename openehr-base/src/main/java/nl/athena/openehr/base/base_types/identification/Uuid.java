package nl.athena.openehr.base.base_types.identification;

import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

import java.util.regex.Pattern;

public class Uuid extends Uid {

    private static final Pattern UUID_PATTERN = Pattern.compile(
            "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    );

    /**
     * Creates a new random {@link Uuid}.
     *
     * @return a new random {@link Uuid}.
     */
    public static Uuid randomUuid() {
        return new Uuid(java.util.UUID.randomUUID().toString());
    }

    /**
     * Creates a new {@link Uuid} with the given value.
     *
     * @param theValue The value.
     */
    public Uuid(String theValue) {
        super(theValue);
        boolean valid = UUID_PATTERN.matcher(theValue).matches();
        if (!valid) {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_UUID_VALUE, theValue));
        }
    }

}
