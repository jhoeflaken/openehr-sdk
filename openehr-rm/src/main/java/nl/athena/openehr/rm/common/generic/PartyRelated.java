package nl.athena.openehr.rm.common.generic;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

/**
 * Proxy type for identifying a party and its relationship to the subject of the record. Use where the relationship
 * between the party and the subject of the record must be known.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/RM/Release-1.1.0/common.html#_party_related_class">
 *     PartyRelated</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class PartyRelated extends PartyIdentified{

    private DvCodedText relationship;

}
