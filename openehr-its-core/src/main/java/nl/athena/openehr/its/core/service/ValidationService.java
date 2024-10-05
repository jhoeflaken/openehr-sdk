package nl.athena.openehr.its.core.service;

import jakarta.annotation.Nonnull;
import nl.athena.openehr.its.core.dto.EhrStatusDto;
import nl.athena.openehr.rm.composition.Composition;

public interface ValidationService {

    void check(Composition theComposition);

    void check(@Nonnull EhrStatusDto theEhrStatus);

}
