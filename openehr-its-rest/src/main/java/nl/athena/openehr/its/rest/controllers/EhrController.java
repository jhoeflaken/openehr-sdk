package nl.athena.openehr.its.rest.controllers;

import nl.athena.openehr.rm.ehr.EhrStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ehr")
public class EhrController {

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EhrStatus> createEhr(
            @RequestBody final EhrStatus ehrStatus) {
        return null;
    }

    @PutMapping(path = "/{ehr_id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<EhrStatus> createEhrWithId(
            @PathVariable("ehr_id") final String ehrId,
            @RequestBody final EhrStatus ehrStatus) {
        return null;
    }

    @GetMapping(path = "/{ehr_id}", produces = "application/json")
    public ResponseEntity<EhrStatus> getEhrStatusById(
            @PathVariable("ehr_id") final String ehrId) {
        return null;
    }

    @GetMapping(path = "/", produces = "application/json")
    public ResponseEntity<EhrStatus> getEhrBySubjectId(
            @RequestParam("subject_id") final String subjectId,
            @RequestParam("subject_namespace") final String subjectNamespace) {
        return null;
    }

}
