package nl.athena.openehr.its.core.mapper;

import jakarta.annotation.Nonnull;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import nl.athena.openehr.rm.ehr.EhrStatus;

public final class EhrStatusMapper {

    public static EhrStatus fromDto(@Nonnull EhrStatusDto theStatus) {
        // TODO Mapping
        return new EhrStatus();
    }

}
