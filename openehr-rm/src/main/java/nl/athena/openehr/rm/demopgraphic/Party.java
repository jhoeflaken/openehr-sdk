package nl.athena.openehr.rm.demopgraphic;

import nl.athena.openehr.base.base_types.identification.LocatableRef;
import nl.athena.openehr.rm.common.archetyped.Locatable;
import nl.athena.openehr.rm.data_structures.item_structure.ItemStructure;
import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public abstract class Party extends Locatable {

    private ItemStructure details;
    private List<PartyIdentity> identities;
    private List<Contact> contacts;
    private List<LocatableRef> reverseRelationships;
    private List<PartyRelationship> relationShips;

    public DvText type() {
        return null;
    }

}
