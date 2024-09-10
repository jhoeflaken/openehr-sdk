package nl.athena.openehr.its.rest.controllers;

import nl.athena.openehr.its.rest.dto.ehr.Ehr;
import nl.athena.openehr.rm.ehr.EhrStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @param subjectId The subject identifier.
     * @param subjectNamespace The namespace of the subject identifier.
     * @return The EHR.
     */
    @GetMapping(produces = "application/json")
    public ResponseEntity<Ehr> getEhrBySubjectId(
            @RequestParam("subject_id") final String subjectId,
            @RequestParam("subject_namespace") final String subjectNamespace) {
        return null;
    }

}
