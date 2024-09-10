package nl.athena.openehr.its.rest.controllers;

import nl.athena.openehr.its.rest.dto.query.AqlQuery;
import nl.athena.openehr.its.rest.dto.query.StoredAqlQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/query")
public class QueryController {

    /**
     * Execute an ad-hoc AQL query.
     *
     * @param q               AQL query
     * @param ehrId           EHR ID
     * @param offset          The offset of the first result to return.
     * @param fetch           The fetch size of the result set.
     * @param queryParameters Query parameters
     * @return AQL query result
     */
    @GetMapping("/query/aql")
    public ResponseEntity<?> executeAdhocAQL(
            @RequestParam(value = "q") final String q,
            @RequestParam(value = "ehr_id", required = false) final String ehrId,
            @RequestParam(value = "offset", required = false) final String offset,
            @RequestParam(value = "fetch", required = false) final Integer fetch,
            @RequestParam(value = "query_parameters", required = false) final Map<String, String> queryParameters) {
        return null;
    }

    /**
     * Execute an ad-hoc AQL query.
     *
     * @param aql AQL query
     * @return AQL query result
     */
    @PostMapping("/query/aql")
    public ResponseEntity<?> executeAdhocAql(
            @RequestBody final AqlQuery aql) {
        return null;
    }

    /**
     * Execute a stored AQL query.
     *
     * @param qualifiedQueryName Qualified query name
     * @param ehrId              EHR ID
     * @param offset             The offset of the first result to return.
     * @param fetch              The fetch size of the result set.
     * @param queryParameters    Query parameters
     * @return AQL query result
     */
    @GetMapping("/query/aql/{qualified_query_name}")
    public ResponseEntity<?> executeStoredAql(
            @PathVariable("qualified_query_name") final String qualifiedQueryName,
            @RequestParam(value = "ehr_id", required = false) final String ehrId,
            @RequestParam(value = "offset", required = false) final String offset,
            @RequestParam(value = "fetch", required = false) final Integer fetch,
            @RequestParam(value = "query_parameters", required = false) final Map<String, String> queryParameters) {
        return null;
    }

    /**
     * Execute a stored AQL query.
     *
     * @param qualifiedQueryName Qualified query name
     * @param aql                AQL query
     * @return AQL query result
     */
    @PostMapping("/query/aql/{qualified_query_name}")
    public ResponseEntity<?> executeStoredAql(
            @PathVariable("qualified_query_name") final String qualifiedQueryName,
            @RequestBody final StoredAqlQuery aql) {
        return null;
    }

    /**
     * Execute a stored AQL query.
     *
     * @param qualifiedQueryName Qualified query name
     * @param version            Version of the query
     * @param ehrId              EHR ID
     * @param offset             The offset of the first result to return.
     * @param fetch              The fetch size of the result set.
     * @param queryParameters    Query parameters
     * @return AQL query result
     */
    @GetMapping("/query/aql/{qualified_query_name}/{version}")
    public ResponseEntity<?> executeStoredAqlVersion(
            @PathVariable("qualified_query_name") final String qualifiedQueryName,
            @PathVariable("version") final String version,
            @RequestParam(value = "ehr_id", required = false) final String ehrId,
            @RequestParam(value = "offset", required = false) final String offset,
            @RequestParam(value = "fetch", required = false) final Integer fetch,
            @RequestParam(value = "query_parameters", required = false) final Map<String, String> queryParameters) {
        return null;
    }

    /**
     * Execute a stored AQL query.
     *
     * @param qualifiedQueryName Qualified query name
     * @param aql                AQL query
     * @return AQL query result
     */
    @PostMapping("/query/aql/{qualified_query_name}/{version}")
    public ResponseEntity<?> executeStoredAqlVersion(
            @PathVariable("qualified_query_name") final String qualifiedQueryName,
            @PathVariable("version") final String version,
            @RequestBody final StoredAqlQuery aql) {
        return null;
    }

}
