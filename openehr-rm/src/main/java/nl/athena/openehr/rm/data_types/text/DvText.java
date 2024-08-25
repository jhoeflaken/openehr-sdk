package nl.athena.openehr.rm.data_types.text;

import nl.athena.openehr.rm.data_types.DataValue;
import nl.athena.openehr.rm.data_types.url.DvUri;

import java.util.List;

public class DvText implements DataValue {

    private String value;
    private DvUri hyperlink;
    private String formatting;
    private CodePhrase language;
    private CodePhrase encoding;
    private List<TermMapping> mappings;

}
