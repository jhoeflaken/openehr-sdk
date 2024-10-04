package nl.athena.openehr.its.core.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OptionsAndConformanceDto(
        @JsonProperty("solution")
        String solution,

        @JsonProperty("solution_version")
        String solutionVersion,

        @JsonProperty("vendor")
        String vendor,

        @JsonProperty("rest_spec_version")
        String restApiSpecsVersion,

        @JsonProperty("conformance_profile")
        String conformanceProfile,

        @JsonProperty("endpoints")
        List<String> endpoints) {
}
