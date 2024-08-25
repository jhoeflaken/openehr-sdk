package nl.athena.openehr.rm.common.generic;

import nl.athena.openehr.rm.data_types.encapsulated.DvMultimedia;
import nl.athena.openehr.rm.data_types.text.DvText;
import nl.athena.openehr.rm.data_types.url.DvEhrUri;

import java.util.List;

public class Attestation {

    private DvMultimedia attestationView;
    private String proof;
    private List<DvEhrUri> items;
    private DvText reason;
    private Boolean isPending;

}
