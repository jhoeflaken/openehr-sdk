package nl.athena.openehr.its.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import nl.athena.openehr.base.base_types.identification.ObjectVersionId;
import nl.athena.openehr.base.base_types.identification.Uuid;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import nl.athena.openehr.rm.common.change_control.OriginalVersion;
import nl.athena.openehr.rm.common.change_control.VersionedObject;
import nl.athena.openehr.rm.common.generic.RevisionHistory;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface EhrService {

    record EhrResult(Uuid ehrId, ObjectVersionId statusVersionId, EhrStatusDto status) {
    }

    EhrResult create(
            @Nullable Uuid theEhrId,
            @Nullable EhrStatusDto theStatus);

    EhrResult updateStatus(
            @Nonnull Uuid theEhrId,
            @Nonnull EhrStatusDto theStatus,
            @Nonnull ObjectVersionId theTargetObjectId,
            @Nonnull Uuid theContributionId,
            @Nonnull Uuid theAuditId);

    EhrResult getStatus(@Nonnull Uuid theEhrId);

    Optional<OriginalVersion<EhrStatusDto>> getEhrStatusAtVersion(
            @Nonnull Uuid theEhrId,
            @Nonnull Uuid theVersionedObjectId,
            int theVersion);

    Optional<Uuid> findBySubjectId(
            @Nonnull String theSubjectId,
            @Nonnull String theSubjectNamespace);

    ObjectVersionId getLatestVersionUidOfStatus(@Nonnull Uuid theEhrId);

    ObjectVersionId getEhrStatusVersionByTimestamp(
            @Nonnull Uuid theEhrId,
            @Nonnull OffsetDateTime theTimestamp);

    DvDateTime getCreationTime(@Nonnull Uuid theEhrId);

    boolean hasEhr(@Nonnull Uuid theEhrId);

    VersionedObject<EhrStatusDto> getVersionedEhrStatus(@Nonnull Uuid theEhrId);

    RevisionHistory getRevisionHistoryOfVersionedEhrStatus(@Nonnull Uuid theEhrId);

    void delete(@Nonnull Uuid theEhrId);

    String getSubjectExternalRef(@Nonnull Uuid theEhrId);

    void checkEhrExists(@Nonnull Uuid theEhrId);

    void checkEhrExistsAndIsModifiable(@Nonnull Uuid theEhrId);

}
