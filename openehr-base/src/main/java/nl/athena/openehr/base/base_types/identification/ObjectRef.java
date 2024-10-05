package nl.athena.openehr.base.base_types.identification;

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
public class ObjectRef {

    protected String namespace;
    protected String type;
    protected ObjectId id;

}
