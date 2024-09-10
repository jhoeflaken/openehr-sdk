package nl.athena.openehr.its.rest.dto.query;

import java.util.Map;

public class StoredAqlQuery {

    private String ehrId;

    private String offset;

    private Integer fetch;

    private Map<String, String> queryParameters;

}
