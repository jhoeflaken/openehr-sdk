package nl.athena.openehr.base.base_types.identification;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

/**
 *
 * Ancestor class of identifiers of informational objects. Ids may be completely meaningless, in which case their only
 * job is to refer to something, or may carry some information to do with the identified object.
 * <br/><br/>
 * Object ids are used inside an object to identify that object. To identify another object in another service, use
 * an {@link ObjectRef}, or else use a {@link Uuid} for local objects identified by UID. If none of the subtypes is
 * suitable, direct instances of this class may be used.
 * <br/><br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/latest/base_types.html#_object_id_class">ObjectId</a>
 */
@Jacksonized
@Getter
@SuperBuilder(setterPrefix = "with", toBuilder = true)
public class ObjectId {

    protected String value;

    public ObjectId(final String theValue) {
        value = theValue;
    }

}
