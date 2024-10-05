package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

import java.util.regex.Pattern;

/**
 * Model of a reverse internet domain, as used to uniquely identify an internet domain. In the form of a dot-separated
 * string in the reverse order of a domain name, specified by IETF RFC 1034.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_internet_id_class">InternetId</a>
 */
@Getter
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "INTERNET_ID")
public class InternetId extends Uid {

    /**
     * Regular expression pattern for internet identifiers conform to the reverse domain name format (RFC 1034).
     */
    private static final Pattern INTERNET_ID_PATTERN = Pattern.compile(
            "^(?!-)[A-Za-z0-9-]{1,63}(?<!-)(\\.(?!-)[A-Za-z0-9-]{1,63}(?<!-))*$"
    );

    /**
     * Creates a new {@link InternetId} with a random value.
     *
     * @param theValue The value.
     * @return A new {@link InternetId} with the given value.
     */
    public static InternetId of(@Nonnull final String theValue) {
        return new InternetId(theValue);
    }

    /**
     * Construct a new internet identifier with the given value.
     *
     * @param theValue The internet identifier.
     */
    public InternetId(@Nonnull final String theValue) {
        super(theValue);
        boolean valid = INTERNET_ID_PATTERN.matcher(theValue).matches();
        if (!valid) {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_INTERNET_ID_VALUE, theValue));
        }
    }

    @Override
    @JsonProperty(value = "_type")
    public String getTypeId() {
        return "INTERNET_ID";
    }

}
