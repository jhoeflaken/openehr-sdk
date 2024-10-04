package nl.athena.openehr.its.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import nl.athena.openehr.base.base_types.builtins.Locale;
import nl.athena.openehr.base.base_types.identification.ObjectVersionId;
import nl.athena.openehr.base.base_types.identification.Uuid;
import nl.athena.openehr.its.core.config.Messages;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import nl.athena.openehr.its.core.exception.StateConflictException;
import nl.athena.openehr.rm.common.change_control.OriginalVersion;
import nl.athena.openehr.rm.common.change_control.VersionedObject;
import nl.athena.openehr.rm.common.generic.PartySelf;
import nl.athena.openehr.rm.common.generic.RevisionHistory;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.text.DvText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultEhrService implements EhrService {

    private final MessageSource messageSource;
    private final Locale locale;

    @Autowired
    public DefaultEhrService(
            @Nonnull MessageSource theMessageSource,
            @Nonnull Locale theLocale) {
        messageSource = theMessageSource;
        locale = theLocale;
    }

    @Override
    public EhrResult create(
            @Nullable Uuid theEhrId,
            @Nullable EhrStatusDto theStatus) {

        // Generate a new EHR ID if it is not provided.
        if (theEhrId == null) {
            theEhrId = new Uuid(UUID.randomUUID().toString());
        }

        // Check if the EHR ID already exists, because it should be unique when creating a new EHR.
        if (hasEhr(theEhrId)) {
            throw new StateConflictException(messageSource.getMessage(Messages.EHR_WITH_ID_ALREADY_EXISTS,
                    new Object[] { theEhrId }, locale.asJavaLocale()));
        }

        // Create a new EHR status if it is not provided.
        if (theStatus == null) {
            theStatus = new EhrStatusDto(
                    null,
                    " openEHR-EHR-EHR_STATUS.generic.v1",
                    new DvText("EHR Status"),
                    null,
                    null,
                    new PartySelf(),
                    true,
                    true,
                    null);
        } else {
            // Validate the EHR status.
        }

        ObjectVersionId statusVersionId = null;
        theStatus = null;

        // Save the EHR to the database.

        return new EhrResult(theEhrId, statusVersionId, theStatus);
    }

    @Override
    public EhrResult updateStatus(
            @Nonnull Uuid theEhrId,
            @Nonnull EhrStatusDto theStatus,
            @Nonnull ObjectVersionId theTargetObjectId,
            @Nonnull Uuid theContributionId,
            @Nonnull Uuid theAuditId) {
        return null;
    }

    @Override
    public EhrResult getStatus(@Nonnull Uuid theEhrId) {
        return null;
    }

    @Override
    public Optional<OriginalVersion<EhrStatusDto>> getEhrStatusAtVersion(@Nonnull Uuid theEhrId, @Nonnull Uuid theVersionedObjectId, int theVersion) {
        return Optional.empty();
    }

    @Override
    public Optional<Uuid> findBySubjectId(@Nonnull String theSubjectId, @Nonnull String theSubjectNamespace) {
        return Optional.empty();
    }

    @Override
    public ObjectVersionId getLatestVersionUidOfStatus(@Nonnull Uuid theEhrId) {
        return null;
    }

    @Override
    public ObjectVersionId getEhrStatusVersionByTimestamp(@Nonnull Uuid theEhrId, @Nonnull OffsetDateTime theTimestamp) {
        return null;
    }

    @Override
    public DvDateTime getCreationTime(@Nonnull Uuid theEhrId) {
        return null;
    }

    @Override
    public boolean hasEhr(@Nonnull Uuid theEhrId) {
        return false;
    }

    @Override
    public VersionedObject<EhrStatusDto> getVersionedEhrStatus(@Nonnull Uuid theEhrId) {
        return null;
    }

    @Override
    public RevisionHistory getRevisionHistoryOfVersionedEhrStatus(@Nonnull Uuid theEhrId) {
        return null;
    }

    @Override
    public void delete(@Nonnull Uuid theEhrId) {

    }

    @Override
    public String getSubjectExternalRef(@Nonnull Uuid theEhrId) {
        return "";
    }

    @Override
    public void checkEhrExists(@Nonnull Uuid theEhrId) {

    }

    @Override
    public void checkEhrExistsAndIsModifiable(@Nonnull Uuid theEhrId) {

    }

}
