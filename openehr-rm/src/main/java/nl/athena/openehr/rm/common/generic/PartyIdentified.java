package nl.athena.openehr.rm.common.generic;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import nl.athena.openehr.rm.data_types.basic.DvIdentifier;

import java.util.List;

/**
 * Proxy data for an identified party other than the subject of the record, minimally consisting of human-readable(s),
 * such as name, formal (and possibly computable) identifiers such as NHS number, and an optional link to external
 * data. There must be at least one of name, identifier or external_ref present.
 * <br/><br/>
 * Used to describe parties where only identifiers may be known, and there is no entry at all in the demographic
 * system (or even no demographic system). Typically for health care providers, e.g. name and provider number of an
 * institution.
 * <br/><br/>
 * Should not be used to include patient identifying information.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/RM/Release-1.1.0/common.html#_party_identified_class">
 *     PartyIdentified</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class PartyIdentified extends PartyProxy {

    private String name;
    private List<DvIdentifier> identifiers;

}
