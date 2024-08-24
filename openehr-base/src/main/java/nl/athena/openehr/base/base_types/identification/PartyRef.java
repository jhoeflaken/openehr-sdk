package nl.athena.openehr.base.base_types.identification;

public class PartyRef extends ObjectRef {

    public Boolean isValidType() {
        return type.equals("PARTY") ||
                type.equals("ORGANISATION") ||
                type.equals("PERSON") ||
                type.equals("GROUP") ||
                type.equals("AGENT") ||
                type.equals("ROLE") ||
                type.equals("ACTOR");
    }

}
