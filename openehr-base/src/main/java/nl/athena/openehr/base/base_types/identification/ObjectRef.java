package nl.athena.openehr.base.base_types.identification;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 *Class describing a reference to another object, which may exist locally or be maintained outside the current
 * namespace, e.g. in another service. Services are usually external, e.g. available in a LAN (including on the same
 * host) or the internet via Corba, SOAP, or some other distributed protocol. However, in small systems they may be
 * part of the same executable as the data containing the id.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_object_ref_class">ObjectRef</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OBJECT_REF")
public class ObjectRef {

    @JsonProperty(value = "namespace", required = true)
    @XmlElement(name = "namespace", required = true)
    protected String namespace;

    @JsonProperty(value = "type", required = true)
    @XmlElement(name = "type", required = true)
    protected String type;

    @JsonProperty(value = "id", required = true)
    @XmlElement(name = "id", required = true)
    protected ObjectId id;

    /**
     * Constructor for ObjectRef.
     *
     * @param theNamespace The namespace of the reference.
     * @param theType The type being referenced.
     * @param theId The id of the reference.
     */
    public ObjectRef(
            final String theNamespace,
            final String theType,
            final ObjectId theId) {
        namespace = theNamespace;
        type = theType;
        id = theId;
    }

    @JsonProperty(value = " _type")
    public String getType() {
        return "OBJECT_REF";
    }

}
