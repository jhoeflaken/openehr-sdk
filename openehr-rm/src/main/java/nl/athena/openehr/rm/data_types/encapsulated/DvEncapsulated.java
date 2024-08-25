package nl.athena.openehr.rm.data_types.encapsulated;

import nl.athena.openehr.rm.data_types.DataValue;
import nl.athena.openehr.rm.data_types.text.CodePhrase;

public abstract class DvEncapsulated implements DataValue {

    protected CodePhrase charset;
    protected CodePhrase language;

}
