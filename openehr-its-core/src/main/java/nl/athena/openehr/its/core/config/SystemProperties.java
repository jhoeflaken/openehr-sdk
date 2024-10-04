package nl.athena.openehr.its.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

    /**
     * Gets the system ID.
     *
     * @return the system ID
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the system ID.
     *
     * @param systemId the system ID to set
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * Gets the solution name.
     *
     * @return the solution name
     */
    public String getSolutionName() {
        return solutionName;
    }

    /**
     * Sets the solution name.
     *
     * @param solutionName the solution name to set
     */
    public void setSolutionName(String solutionName) {
        this.solutionName = solutionName;
    }

    /**
     * Gets the solution version.
     *
     * @return the solution version
     */
    public String getSolutionVersion() {
        return solutionVersion;
    }

    /**
     * Sets the solution version.
     *
     * @param solutionVersion the solution version to set
     */
    public void setSolutionVersion(String solutionVersion) {
        this.solutionVersion = solutionVersion;
    }

    /**
     * Gets the REST API specifications version.
     *
     * @return the REST API specifications version
     */
    public String getRestApiSpecsVersion() {
        return restApiSpecsVersion;
    }

    /**
     * Sets the REST API specifications version.
     *
     * @param restApiSpecsVersion the REST API specifications version to set
     */
    public void setRestApiSpecsVersion(String restApiSpecsVersion) {
        this.restApiSpecsVersion = restApiSpecsVersion;
    }

    /**
     * Gets the vendor.
     *
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the vendor.
     *
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * Gets the conformance profile.
     *
     * @return the conformance profile
     */
    public String getConformanceProfile() {
        return conformanceProfile;
    }

    /**
     * Sets the conformance profile.
     *
     * @param conformanceProfile the conformance profile to set
     */
    public void setConformanceProfile(String conformanceProfile) {
        this.conformanceProfile = conformanceProfile;
    }

    /**
     * Gets the endpoints.
     *
     * @return the endpoints
     */
    public List<String> getEndpoints() {
        return endpoints;
    }

    /**
     * Sets the endpoints.
     *
     * @param endpoints the endpoints to set
     */
    public void setEndpoints(List<String> endpoints) {
        this.endpoints = endpoints;
    }

}
