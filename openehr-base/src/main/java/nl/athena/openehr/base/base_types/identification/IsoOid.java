package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.Messages;
import nl.athena.openehr.util.i18n.I18n;

import java.util.regex.Pattern;

/**
 * Model of ISO’s Object Identifier (oid) as defined by the standard ISO/IEC 8824. Oids are formed from integers
 * separated by dots. Each non-leaf node in an Oid starting from the left corresponds to an assigning authority,
 * and identifies that authority’s namespace, inside which the remaining part of the identifier is locally unique.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_iso_oid_class">IsoOid</a>
 */
@Getter
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlType(name = "ISO_OID")
public class IsoOid extends Uid {

    /**
     * Regular expression pattern for ISO Object Identifiers (OIDs) conform to the format specified by ISO/IEC 8824.
     */
    private static final Pattern ISO_OID_PATTERN = Pattern.compile(
            "^\\d+(\\.\\d+)*$"
    );

    /**
     * Creates a new {@link IsoOid} with a random value.
     *
     * @param theValue The value.
     * @return A new {@link IsoOid} with the given value.
     */
    public static IsoOid of(@Nonnull final String theValue) {
        return new IsoOid(theValue);
    }

    /**
     * Creates a new {@link IsoOid} with the given value.
     *
     * @param theValue The value.
     */
    public IsoOid(@Nonnull final String theValue) {
        super(theValue);
        boolean valid = ISO_OID_PATTERN.matcher(theValue).matches();
        if (!valid) {
            throw new IllegalArgumentException(I18n.getMessage(Messages.INVALID_ISO_OID, theValue));
        }
    }

    @Override
    @JsonProperty(value = "_type")
    public String getTypeId() {
        return "ISO_OID";
    }

}
