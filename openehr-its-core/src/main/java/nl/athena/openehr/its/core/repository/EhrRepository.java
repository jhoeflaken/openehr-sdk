package nl.athena.openehr.its.core.repository;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import nl.athena.openehr.base.base_types.identification.Uuid;
import nl.athena.openehr.rm.ehr.EhrStatus;

/**
 * Defines the interface for the EHR repository.
 */
public interface EhrRepository extends OpenEhrRepository {

    void insert(
            @Nonnull final Uuid theEhrId,
            @Nonnull final EhrStatus theStatus,
            @Nullable final Uuid theContributionId,
            @Nullable final Uuid theAuditId);

    boolean hasEhr(@Nonnull Uuid theEhrId);



}
