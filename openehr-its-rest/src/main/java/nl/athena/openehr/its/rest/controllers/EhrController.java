package nl.athena.openehr.its.rest.controllers;

import nl.athena.openehr.its.rest.dto.ehr.Ehr;
import nl.athena.openehr.rm.common.change_control.Contribution;
import nl.athena.openehr.rm.common.change_control.Version;
import nl.athena.openehr.rm.common.directory.Folder;
import nl.athena.openehr.rm.common.generic.RevisionHistoryItem;
import nl.athena.openehr.rm.composition.Composition;
import nl.athena.openehr.rm.ehr.EhrStatus;
import nl.athena.openehr.rm.ehr.VersionedComposition;
import nl.athena.openehr.rm.ehr.VersionedEhrStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ehr")
public class EhrController {

    /**
     * Create a new EHR with an auto-generated identifier.
     *
     * @param ehrStatus The optional {@link EhrStatus} describing the EHR.
     * @return The {@link EhrStatus} of the created EHR.
     */
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<EhrStatus> createEhr(
            @RequestBody final EhrStatus ehrStatus) {
        return null;
    }

    /**
     * Create a new EHR with the specified identifier.
     *
     * @param ehrId     The EHR identifier to use.
     * @param ehrStatus The optional {@link EhrStatus} describing the EHR.
     * @return The {@link EhrStatus} of the created EHR.
     */
    @PutMapping(path = "/{ehr_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EhrStatus> createEhrWithId(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final EhrStatus ehrStatus) {
        return null;
    }

    /**
     * Get the EHR with the specified identifier.
     *
     * @param ehrId The identifier of the EHR.
     * @return The EHR.
     */
    @GetMapping(path = "/{ehr_id}", produces = "application/json")
    public ResponseEntity<Ehr> getEhrStatusById(
            @PathVariable("ehr_id") final String ehrId) {
        return null;
    }

    /**
     * Get the EHR with the specified subject identifier.
     *
     * @param subjectId        The subject identifier.
     * @param subjectNamespace The namespace of the subject identifier.
     * @return The EHR.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Ehr> getEhrBySubjectId(
            @RequestParam("subject_id") final String subjectId,
            @RequestParam("subject_namespace") final String subjectNamespace) {
        return null;
    }

    /**
     * Get the {@link EhrStatus} with the specified version identifier.
     *
     * @param ehrId      The identifier of the EHR.
     * @param versionUid The version identifier.
     * @return The {@link EhrStatus}.
     */
    @GetMapping(path = "/{ehr_id}/ehr_status/{version_uid}", produces = "application/json")
    public ResponseEntity<EhrStatus> getEhrStatusByVersionId(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("version_uid") final String versionUid) {
        return null;
    }

    /**
     * Get the {@link EhrStatus} at the specified time.
     *
     * @param ehrId         The identifier of the EHR.
     * @param versionAtTime The time to get the EHR status at. If not specified, the current status is returned.
     * @return The {@link EhrStatus}.
     */
    @GetMapping(path = "/{ehr_id}/ehr_status", produces = "application/json")
    public ResponseEntity<EhrStatus> getEhrStatusAtTime(
            @PathVariable("ehr_id") final String ehrId,
            @RequestParam(name = "version_at_time", required = false) final String versionAtTime) {
        return null;
    }

    /**
     * Update the EHR status.
     *
     * @param ehrId     The identifier of the EHR.
     * @param ehrStatus The new {@link EhrStatus}.
     * @return The updated {@link EhrStatus}.
     */
    @PutMapping(path = "/{ehr_id}/ehr_status", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EhrStatus> updateEhrStatus(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final EhrStatus ehrStatus) {
        return null;
    }

    /**
     * Get the versioned {@link EhrStatus}.
     *
     * @param ehrId The identifier of the EHR.
     * @return The versioned {@link VersionedEhrStatus}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_ehr_status", produces = "application/json")
    public ResponseEntity<VersionedEhrStatus> getVersionedEhrStatus(
            @PathVariable("ehr_id") final String ehrId) {
        return null;
    }

    /**
     * Get the versioned {@link EhrStatus} revision history.
     *
     * @param ehrId The identifier of the EHR.
     * @return The list with {@link RevisionHistoryItem}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_ehr_status/revision_history", produces = "application/json")
    public ResponseEntity<List<RevisionHistoryItem>> getVersionedEhrStatusRevisionHistory(
            @PathVariable("ehr_id") final String ehrId) {
        return null;
    }

    /**
     * Get the versioned {@link EhrStatus} at the specified time.
     *
     * @param ehrId         The identifier of the EHR.
     * @param versionAtTime The time to get the EHR status at. If not specified, the current status is returned.
     * @return The {@link Version}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_ehr_status/version", produces = "application/json")
    public ResponseEntity<Version<?>> getVersionedEhrStatusVersionAtTime(
            @PathVariable("ehr_id") final String ehrId,
            @RequestParam(name = "version_at_time", required = false) final String versionAtTime) {
        return null;
    }

    /**
     * Get the versioned {@link EhrStatus} with the specified version identifier.
     *
     * @param ehrId      The identifier of the EHR.
     * @param versionUid The version identifier.
     * @return The {@link Version}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_ehr_status/version/{version_uid}", produces = "application/json")
    public ResponseEntity<Version<?>> getVersionedEhrStatusById(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("version_uid") final String versionUid) {
        return null;
    }

    /**
     * Create a new composition in the EHR.
     *
     * @param ehrId       The identifier of the EHR.
     * @param composition The {@link Composition} to create.
     * @return The created {@link Composition}.
     */
    @PostMapping(path = "/{ehr_id}/composition", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Composition> createComposition(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final Composition composition) {
        return null;
    }

    /**
     * Get the composition with the specified identifier.
     *
     * @param ehrId      The identifier of the EHR.
     * @param uidBasedId The identifier of the composition.
     * @return The {@link Composition}.
     */
    @GetMapping(path = "/{ehr_id}/composition/{uid_based_id}", produces = "application/json")
    public ResponseEntity<Composition> getComposition(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("uid_based_id") final String uidBasedId) {
        return null;
    }

    /**
     * Update the composition with the specified identifier.
     *
     * @param ehrId       The identifier of the EHR.
     * @param uidBasedId  The identifier of the composition.
     * @param composition The updated {@link Composition}.
     * @return The updated {@link Composition}.
     */
    @PutMapping(path = "/{ehr_id}/composition/{uid_based_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Composition> updateComposition(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("uid_based_id") final String uidBasedId,
            @RequestBody final Composition composition) {
        return null;
    }

    /**
     * Delete the composition with the specified identifier.
     *
     * @param ehrId      The identifier of the EHR.
     * @param uidBasedId The identifier of the composition.
     * @return The HTTP response.
     */
    @DeleteMapping(path = "/{ehr_id}/composition/{uid_based_id}")
    public ResponseEntity<Void> deleteComposition(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("uid_based_id") final String uidBasedId) {
        return null;
    }

    /**
     * Get the versioned composition with the specified identifier.
     *
     * @param ehrId             The identifier of the EHR.
     * @param versionedObjectId identifier of the composition.
     * @return The {@link VersionedComposition}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_composition/{versioned_object_id}", produces = "application/json")
    public ResponseEntity<VersionedComposition> getVersionedComposition(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("versioned_object_id") final String versionedObjectId) {
        return null;
    }

    /**
     * Get the versioned composition revision history.
     *
     * @param ehrId             The identifier of the EHR.
     * @param versionedObjectId identifier of the composition.
     * @return The revision history of the {@link VersionedComposition}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_composition/{versioned_object_id}/revision_history", produces = "application/json")
    public ResponseEntity<List<RevisionHistoryItem>> getVersionedCompositionRevisionHistory(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("versioned_object_id") final String versionedObjectId) {
        return null;
    }

    /**
     * Get the versioned composition with the specified version identifier at the specified time.
     *
     * @param ehrId             The identifier of the EHR.
     * @param versionedObjectId identifier of the composition.
     * @param versionAtTime     The time to get the composition at. If not specified, the current composition is returned.
     * @return The {@link VersionedComposition} at the specified time or the latest version if not specified.
     */
    public ResponseEntity<VersionedComposition> getVersionedCompositionVersionAtTime(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("versioned_object_id") final String versionedObjectId,
            @RequestParam(name = "version_at_time", required = false) final String versionAtTime) {
        return null;
    }

    /**
     * Get the versioned composition with the specified version identifier.
     *
     * @param ehrId             The identifier of the EHR.
     * @param versionedObjectId identifier of the composition.
     * @param versionUid        The version identifier.
     * @return The {@link VersionedComposition}.
     */
    @GetMapping(path = "/{ehr_id}/versioned_composition/{versioned_object_id}/version/{version_uid}", produces = "application/json")
    public ResponseEntity<VersionedComposition> getVersionedCompositionByVersionId(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("versioned_object_id") final String versionedObjectId,
            @PathVariable("version_uid") final String versionUid) {
        return null;
    }

    /**
     * Create a new directory in the EHR.
     *
     * @param ehrId     The identifier of the EHR.
     * @param directory The {@link Folder} to create.
     * @return The created {@link Folder}.
     */
    @PostMapping(path = "/{ehr_id}/directory", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Folder> createDirectory(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final Folder directory) {
        return null;
    }

    /**
     * Update the directory with the specified identifier.
     *
     * @param ehrId     The identifier of the EHR.
     * @param directory The updated {@link Folder}.
     * @return The updated {@link Folder}.
     */
    @PutMapping(path = "/{ehr_id}/directory", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Folder> updateDirectory(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final Folder directory) {
        return null;
    }

    /**
     * Delete the directory with the specified identifier.
     *
     * @param ehrId The identifier of the EHR.
     * @return The HTTP response.
     */
    @DeleteMapping(path = "/{ehr_id}/directory")
    public ResponseEntity<Void> deleteDirectory(
            @PathVariable("ehr_id") final String ehrId) {
        return null;
    }

    /**
     * Get the folder in a directory at the specified time. If not specified, the current directory is returned.
     *
     * @param ehrId         The identifier of the EHR.
     * @param versionAtTime The time to get the directory at. If not specified, the current directory is returned.
     * @return The {@link Folder}.
     */
    @GetMapping(path = "/{ehr_id}/directory", produces = "application/json")
    public ResponseEntity<Folder> getDFolderInDirectoryAtTime(
            @PathVariable("ehr_id") final String ehrId,
            @RequestParam(name = "path", required = false) final String path,
            @RequestParam("version_at_time") final String versionAtTime) {
        return null;
    }

    /**
     * Get the folder in a directory with the specified version identifier.
     *
     * @param ehrId      The identifier of the EHR.
     * @param versionUid The version identifier.
     * @return The {@link Folder}.
     */
    @GetMapping(path = "/{ehr_id}/directory/{version_uid}", produces = "application/json")
    public ResponseEntity<Folder> getFolderInDirectoryVersion(
            @PathVariable("ehr_id") final String ehrId,
            @RequestParam(name = "path", required = false) final String path,
            @RequestParam("version_uid") final String versionUid) {
        return null;
    }

    /**
     * Create a new contribution in the EHR.
     *
     * @param ehrId        The identifier of the EHR.
     * @param contribution The {@link Contribution} to create.
     * @return The created {@link Contribution}.
     */
    @PostMapping(path = "/{ehr_id}/contribution", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Contribution> createContribution(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final Contribution contribution) {
        return null;
    }

    /**
     * Get the contribution with the specified identifier.
     *
     * @param ehrId           The identifier of the EHR.
     * @param contributionUid The identifier of the contribution.
     * @return The {@link Contribution}.
     */
    @GetMapping(path = "/{ehr_id}/contribution/{contribution_uid}", produces = "application/json")
    public ResponseEntity<Contribution> getContributionById(
            @PathVariable("ehr_id") final String ehrId,
            @PathVariable("contribution_uid") final String contributionUid) {
        return null;
    }

}