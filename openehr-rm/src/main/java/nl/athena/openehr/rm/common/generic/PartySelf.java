package nl.athena.openehr.rm.common.generic;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 * Party proxy representing the subject of the record. Used to indicate that the party is the owner of the record.
 * May or may not have external_ref set. See <a href="https://specifications.openehr.org/releases/RM/Release-1.1.0/common.html#_party_self_class">
 *     PartySelf</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class PartySelf extends PartyProxy {

}
