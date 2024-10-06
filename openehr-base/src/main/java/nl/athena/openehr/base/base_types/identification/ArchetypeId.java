package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.util.Arrays;
import java.util.StringJoiner;

@Getter
@Jacksonized
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ARCHETYPE_ID")
public class ArchetypeId extends ObjectId {

    @JsonIgnore
    @XmlTransient
    private final String originator;

    @JsonIgnore
    @XmlTransient
    private final String name;

    @JsonIgnore
    @XmlTransient
    private final String entity;

    @JsonIgnore
    @XmlTransient
    private final String concept;

    @JsonIgnore
    @XmlTransient
    private final String[] specialisation;

    @JsonIgnore
    @XmlTransient
    private final String version;

    /**
     * Constructs a new ArchetypeId with the given value.
     *
     * @param theValue The value of the ArchetypeId.
     */
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

    @Override
    @JsonProperty(value = "_type")
    public String getType() {
        return "ARCHETYPE_ID";
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
