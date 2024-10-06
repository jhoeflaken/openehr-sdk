package nl.athena.openehr.its.core.repository;

import jakarta.annotation.Nonnull;
import nl.athena.openehr.base.base_types.identification.ObjectVersionId;
import nl.athena.openehr.base.base_types.identification.Uuid;

public interface OpenEhrRepository {

    default ObjectVersionId newObjectVersionId(
            @Nonnull final Uuid theVersionedObjectId,
            @Nonnull final String theSystemId,
            final int theSystemVersion) {
        return new ObjectVersionId(theVersionedObjectId.toString() + "::" + theSystemId + "::" +
                theSystemVersion);
    }

}
