package nl.athena.openehr.its.rest.controller;

import nl.athena.openehr.its.rest.exception.InvalidApiParameterException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * Base class for all OpenEHR ITS REST controllers.
 */
public abstract class BaseController {

    // OpenEHR HTTP headers.
    public static final String HEADER_PREFER = "Prefer";
    public static final String HEADER_OPENEHR_VERSION = "openEHR-VERSION";
    public static final String HEADER_OPENEHR_AUDIT_DETAILS = "openEHR-AUDIT-DETAILS";
    public static final String HEADER_CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
    public static final String HEADER_ACCEPT = HttpHeaders.ACCEPT;
    public static final String HEADER_LOCATION = HttpHeaders.LOCATION;
    public static final String HEADER_ETAG = HttpHeaders.ETAG;
    public static final String HEADER_LAST_MODIFIED = HttpHeaders.LAST_MODIFIED;
    public static final String HEADER_IF_MATCH = HttpHeaders.IF_MATCH;

    // Constants for Prefer header values.
    public static final String RETURN_MINIMAL = "return=minimal";
    public static final String RETURN_REPRESENTATION = "return=representation";


    // OpenEHR resource names.
    public static final String RESOURCE_EHR = "ehr";
    public static final String RESOURCE_EHR_STATUS = "ehr_status";
    public static final String RESOURCE_VERSIONED_EHR_STATUS = "versioned_ehr_status";
    public static final String RESOURCE_VERSIONED_COMPOSITION = "versioned_composition";
    public static final String RESOURCE_COMPOSITION = "composition";
    public static final String RESOURCE_DIRECTORY = "directory";
    public static final String RESOURCE_CONTRIBUTION = "contribution";
    public static final String RESOURCE_QUERY = "query";
    public static final String RESOURCE_DEFINITION = "definition";
    public static final String RESOURCE_TEMPLATE = "template";

    // OpenEHR context paths.
    public static final String CONTEXT_PATH_OPENEHR = "/api/openehr";
    public static final String CONTEXT_PATH_OPENEHR_WITH_VERSION = CONTEXT_PATH_OPENEHR + "/v1";
    public static final String CONTEXT_PATH_ADMIN = "/api/admin";
    public static final String CONTEXT_PATH_ADMIN_WITH_VERSION = CONTEXT_PATH_ADMIN + "/v1";


    @Value(CONTEXT_PATH_OPENEHR_WITH_VERSION)
    protected String openEhrContextPath;

    /**
     * Get the absolute path of the current request.
     *
     * @return The absolute path of the current request.
     */
    public String getAbsolutePath() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    /**
     * Returns a URI for list of segments. The segments are appended to the base path and encoded to ensure safe usage
     * in a URI.
     *
     * @param thePathSegments List of segments to append to the base URL
     * @return URI for the given base URL and segments
     */
    protected URI createLocationUri(String... thePathSegments) {
        return UriComponentsBuilder.fromHttpUrl(getAbsolutePath())
                .pathSegment(UriUtils.encodePath(openEhrContextPath, StandardCharsets.UTF_8))
                .build()
                .toUri();
    }

    /**
     * Helper to parse a string in UUID-format to a {@link UUID} instance.
     *
     * @param theUuidString The UUID string to parse
     * @param theError      The error message to use when the UUID string is invalid
     * @return The {@link UUID} instance parsed from the given string.
     * @throws InvalidApiParameterException If the given <code>uuidString</code> is invalid.
     */
    protected UUID parseUUID(
            final String theUuidString,
            final String theError) {
        try {
            return UUID.fromString(theUuidString);
        } catch (IllegalArgumentException e) {
            throw new InvalidApiParameterException(theError);
        }
    }



}
