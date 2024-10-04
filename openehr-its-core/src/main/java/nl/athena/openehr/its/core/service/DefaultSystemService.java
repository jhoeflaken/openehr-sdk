package nl.athena.openehr.its.core.service;

import jakarta.annotation.Nonnull;
import nl.athena.openehr.its.core.config.SystemProperties;
import nl.athena.openehr.its.core.dto.OptionsAndConformanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default implementation of the {@link SystemService}.
 */
@Service
public class DefaultSystemService implements SystemService {

    private final SystemProperties systemProperties;

    @Autowired
    public DefaultSystemService(@Nonnull SystemProperties theSystemProperties) {
        systemProperties = theSystemProperties;
    }

    @Override
    public String getSystemId() {
        return systemProperties.getSystemId();
    }

    @Override
    public OptionsAndConformanceDto getOptionsAndConformance() {
        return new OptionsAndConformanceDto(
                systemProperties.getSolutionName(),
                systemProperties.getSolutionVersion(),
                systemProperties.getVendor(),
                systemProperties.getRestApiSpecsVersion(),
                systemProperties.getConformanceProfile(),
                systemProperties.getEndpoints()
        );
    }

}
