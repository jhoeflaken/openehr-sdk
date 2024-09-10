package nl.athena.openehr.its.rest.dto.query;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public class AqlQuery {

    @NotBlank
    private String q;

    private String offset;

    private Integer fetch;

    private Map<String, String> queryParameters;

}
