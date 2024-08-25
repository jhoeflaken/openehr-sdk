package nl.athena.openehr.rm.ehr.data_types.text;

import nl.athena.openehr.rm.ehr.data_types.DataValue;

import java.util.List;

public class DvText implements DataValue {

    private String value;
    private DvUri hyperlink;
    private String formatting;
    private CodePhrase language;
    private CodePhrase encoding;
    private List<TermMapping> mappings;

}
