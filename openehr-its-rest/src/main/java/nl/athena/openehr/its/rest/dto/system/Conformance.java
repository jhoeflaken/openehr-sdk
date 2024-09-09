package nl.athena.openehr.its.rest.dto.system;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Conformance {

    @JsonProperty("solution")
    private String solution;

    @JsonProperty("solution_version")
    private String solutionVersion;

    @JsonProperty("vendor")
    private String vendor;

    @JsonProperty("restapi_specs_version")
    private String restapiSpecsVersion;

    @JsonProperty("conformance_profile")
    private String conformanceProfile;;

    @JsonProperty("endpoints")
    private List<String> endpoints;

}
