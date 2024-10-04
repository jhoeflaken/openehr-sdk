package nl.athena.openehr.its.rest.controller;

import nl.athena.openehr.its.core.service.EhrService;
import nl.athena.openehr.its.core.service.SystemService;
import nl.athena.openehr.its.core.dto.EhrDto;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static nl.athena.openehr.its.rest.controller.BaseController.*;

@RestController
@RequestMapping(path = BaseController.CONTEXT_PATH_OPENEHR_WITH_VERSION + "/" + BaseController.RESOURCE_EHR)
public class EhrController {

    private final EhrService ehrService;
    private final SystemService systemService;

    /**
     * Create a new instance of the OpenEHR EHR API controller.
     *
     * @param theEhrService    The {@link EhrService} to use to manage EHR instances
     * @param theSystemService The {@link SystemService} to system service to use.
     */
    @Autowired
    public EhrController(
            final EhrService theEhrService,
            final SystemService theSystemService) {
        ehrService = theEhrService;
        systemService = theSystemService;
    }

    /**
     * Create a new EHR with an auto-generated identifier.
     *
     * @param theEhrStatus The optional {@link EhrStatusDto} describing the EHR.
     * @return The  created EHR when prefer is set to return=representation, otherwise the location of the created EHR
     * in the <code>Location</code> header.
     */
    @PostMapping(
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EhrDto> createEhr(
            @RequestHeader(value = HEADER_OPENEHR_VERSION, required = false) final String theOpenEhrVersion,
            @RequestHeader(value = HEADER_OPENEHR_AUDIT_DETAILS, required = false) final String theAuditDetails,
            @RequestHeader(value = HEADER_PREFER, required = false, defaultValue = RETURN_MINIMAL) final String thePrefer,
            @RequestBody final EhrStatusDto theEhrStatus) {
        return null;
    }

}