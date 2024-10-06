package nl.athena.openehr.its.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "athena.openehr.system")
public class SystemProperties {

    private String systemId;
    private String solutionName;
    private String solutionVersion;
    private String restApiSpecsVersion;
    private String vendor;
    private String conformanceProfile;
    private List<String> endpoints;

}
