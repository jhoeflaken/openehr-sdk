package nl.athena.openehr.rm.common.generic;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.base.base_types.identification.PartyRef;

/**
 *
 * Abstract concept of a proxy description of a party, including an optional link to data for this party in a
 * demographic or other identity management system. Sub-typed into {@link PartyIdentified} and {@link PartySelf}.
 * See <a href="https://specifications.openehr.org/releases/RM/Release-1.1.0/common.html#_party_proxy_class">
 *     PartyProxy</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class PartyProxy {

    private PartyRef externalRef;

}
