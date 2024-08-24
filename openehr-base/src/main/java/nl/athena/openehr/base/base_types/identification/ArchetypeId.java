package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Arrays;
import java.util.StringJoiner;

public class ArchetypeId extends ObjectId {

    @JsonIgnore
    private final String originator;

    @JsonIgnore
    private final String name;

    @JsonIgnore
    private final String entity;

    @JsonIgnore
    private final String concept;

    @JsonIgnore
    private final String[] specialisation;

    @JsonIgnore
    private final String version;

    public ArchetypeId(final String theValue) {
        super(theValue);

        String[] parts = theValue.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid archetype id: " + theValue);
        }

        String[] fqdnParts = parts[0].split("-");
        if (fqdnParts.length != 3) {
            throw new IllegalArgumentException("Invalid archetype id: " + theValue);
        }

        String[] conceptParts = parts[1].split("-");

        originator = fqdnParts[0];
        name = fqdnParts[1];
        entity = fqdnParts[2];
        concept = conceptParts[0];
        specialisation = Arrays.copyOfRange(conceptParts, 1, conceptParts.length);
        version = parts[2];
    }

    public String qualifiedRmEntity() {
        return originator + "-"  + name + "-" + entity;
    }

    public String domainConcept() {
        StringJoiner joiner = new StringJoiner("-");
        joiner.add(concept);
        if (specialisation != null) {
            for (String spec : specialisation) {
                joiner.add(spec);
            }
        }

        return joiner.toString();
    }

    public String rmOriginator() {
        return originator;
    }

    public String rmName() {
        return name;
    }

    public String rmEntity() {
        return entity;
    }

    public String specialisation() {
        StringJoiner joiner = new StringJoiner("-");
        if (specialisation != null) {
            for (String spec : specialisation) {
                joiner.add(spec);
            }
        }

        return joiner.toString();
    }

    public String versionId() {
        return version;
    }

}
