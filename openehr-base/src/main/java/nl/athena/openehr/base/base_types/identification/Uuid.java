package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

import java.util.regex.Pattern;

/**
 * Model of the DCE Universal Unique Identifier or UUID which takes the form of hexadecimal integers separated by
 * hyphens, following the pattern 8-4-4-4-12 as defined by the Open Group, CDE 1.1 Remote Procedure Call specification,
 * Appendix A. Also known as a GUID.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_uuid_class">Uuid</a>
 */
@Getter
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UUID")
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
     * @return A new {@link Uuid} with the given value.
     */
    public static Uuid of(@Nonnull final String theValue) {
        return new Uuid(theValue);
    }

    /**
     * Creates a new {@link Uuid} with the given value.
     *
     * @param theValue The value.
     */
    private Uuid(@Nonnull final String theValue) {
        super(theValue);
        boolean valid = UUID_PATTERN.matcher(theValue).matches();
        if (!valid) {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_UUID_VALUE, theValue));
        }
    }

    @Override
    @JsonProperty(value = "_type")
    public String getTypeId() {
        return "UUID";
    }

}
