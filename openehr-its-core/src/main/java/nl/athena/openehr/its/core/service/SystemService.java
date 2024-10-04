package nl.athena.openehr.its.core.service;

import nl.athena.openehr.its.core.dto.OptionsAndConformanceDto;

/**
 * Service to provide information about the OpenEHR system.
 */
public interface SystemService {

    /**
     * Get the system identifier which is used to identify the OpenEHR system, for example in the audit log.
     *
     * @return The system identifier.
     */
    String getSystemId();

    /**
     * Get the options and conformance of the system.
     *
     * @return The options and conformance of the OpenEHR implementation.
     */
    OptionsAndConformanceDto getOptionsAndConformance();

}
