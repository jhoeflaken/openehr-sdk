package nl.athena.openehr.its.core.service;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import nl.athena.openehr.base.base_types.identification.ObjectVersionId;
import nl.athena.openehr.base.base_types.identification.PartyRef;
import nl.athena.openehr.base.base_types.identification.Uuid;
import nl.athena.openehr.its.core.config.Messages;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import nl.athena.openehr.its.core.exception.InternalServerException;
import nl.athena.openehr.its.core.exception.StateConflictException;
import nl.athena.openehr.its.core.exception.UnprocessableEntityException;
import nl.athena.openehr.its.core.exception.ValidationException;
import nl.athena.openehr.its.core.mapper.EhrStatusMapper;
import nl.athena.openehr.its.core.repository.EhrRepository;
import nl.athena.openehr.rm.common.change_control.OriginalVersion;
import nl.athena.openehr.rm.common.change_control.VersionedObject;
import nl.athena.openehr.rm.common.generic.PartyProxy;
import nl.athena.openehr.rm.common.generic.PartySelf;
import nl.athena.openehr.rm.common.generic.RevisionHistory;
import nl.athena.openehr.rm.data_types.quantity.date_time.DvDateTime;
import nl.athena.openehr.rm.data_types.text.DvText;
import nl.athena.openehr.util.i18n.I18n;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class DefaultEhrService implements EhrService {

    private final EhrRepository ehrRepository;
    private final ValidationService validationService;
    private final SystemService systemService;

    @Autowired
    public DefaultEhrService(
            final EhrRepository theEhrRepository,
            final ValidationService theValidationService,
            final SystemService theSystemService) {
        ehrRepository = theEhrRepository;
        validationService = theValidationService;
        systemService = theSystemService;
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
            throw new StateConflictException(I18n.getMessage(Messages.EHR_WITH_ID_ALREADY_EXISTS, theEhrId));
        }

        // Create a new EHR status if it is not provided.
        if (theStatus == null) {
            theStatus = new EhrStatusDto(
                    null,
                    " openEHR-EHR-EHR_STATUS.generic.v1",
                    new DvText("EHR Status"),
                    null,
                    null,
                    PartySelf.builder()
                            .build(),
                    true,
                    true,
                    null);
        } else {
            // Validate the EHR status.
            check(theStatus);
            checkNoEhrExistsForParty(theEhrId, theStatus);
        }

        // The server always sets its own UUID for the EHR status, whether it is provided or not.
        ObjectVersionId statusVersionId = buildObjectVersionId(Uuid.randomUuid(), systemService.getSystemId(), 1);
        theStatus = ehrStatusDtoWithId(theStatus, statusVersionId);

        ehrRepository.insert(theEhrId, EhrStatusMapper.fromDto(theStatus), null, null);

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
        return ehrRepository.hasEhr(theEhrId);
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

    /**
     * Check the EHR status for validation errors.
     *
     * @param theStatus The EHR status to check.
     */
    private void check(@Nonnull EhrStatusDto theStatus) {
        try {
            validationService.check(theStatus);
        } catch (Exception e) {
            switch (e) {
                case UnprocessableEntityException ex -> throw ex;
                case ValidationException ex -> throw ex;
                default -> throw new InternalServerException(e.getMessage(), e);
            }
        }
    }

    /**
     * Check that if a party is specified in the EHR status, it is not already used by another EHR. This would mean
     * that the party is already associated with an EHR and cannot be associated with another EHR.
     *
     * @param theEhrId  The EHR id to check for.
     * @param theStatus The EHR status to check.
     */
    private void checkNoEhrExistsForParty(
            Uuid theEhrId,
            @Nullable EhrStatusDto theStatus) {
        Optional<PartyRef> partyRef = Optional.ofNullable(theStatus)
                .map(EhrStatusDto::subject)
                .map(PartyProxy::getExternalRef);

        if (partyRef.isPresent()) {
            final PartyRef ref = partyRef.get();
            final String subjectId = ref.getId().getValue();
            final String namespace = ref.getNamespace();
            final Optional<Uuid> partyEhrId = findBySubjectId(subjectId, namespace);
            if (partyEhrId.isPresent() && !partyEhrId.get().equals(theEhrId)) {
                throw new StateConflictException(I18n.getMessage(Messages.PARTY_USED_BY_OTHER_EHR, subjectId, namespace));
            }
        }
    }

    /**
     * Build an object version id.
     *
     * @param theVersionedObjectId The versioned object id.
     * @param theSystemId          The system id.
     * @param theSystemVersion     The system version.
     * @return The object version id.
     */
    private ObjectVersionId buildObjectVersionId(
            @Nonnull final Uuid theVersionedObjectId,
            @Nonnull final String theSystemId,
            final int theSystemVersion) {
        return ehrRepository.newObjectVersionId(theVersionedObjectId, theSystemId, theSystemVersion);
    }

    /**
     * Add the object version id to the EHR status. Note that the EHR status is immutable, so a new instance is
     * created.
     *
     * @param theStatus    The EHR status.
     * @param theVersionId The object version id.
     * @return The EHR status with the object version id.
     */
    private EhrStatusDto ehrStatusDtoWithId(
            @Nonnull final EhrStatusDto theStatus,
            @Nonnull final ObjectVersionId theVersionId) {
        return theStatus.toBuilder()
                .withUid(theVersionId)
                .build();
    }

}
