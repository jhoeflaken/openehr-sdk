package nl.athena.openehr.its.rest.controllers;

import nl.athena.openehr.its.rest.dto.system.Conformance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SystemController {

    @RequestMapping(method = RequestMethod.OPTIONS, produces = "application/json; charset=utf-8")
    public ResponseEntity<Conformance> getSystemOptionsAndConformance() {
        return null;
    }

}
