package nl.athena.openehr.rm.common.change_control;

import nl.athena.openehr.base.base_types.identification.ObjectVersionId;
import nl.athena.openehr.rm.data_types.text.DvCodedText;

public class OriginalVersion<T> extends Version<T> {

    private ObjectVersionId uid;
    private ObjectVersionId precedingVersionUid;
    private DvCodedText lifecycleState;
    private T data;

}
