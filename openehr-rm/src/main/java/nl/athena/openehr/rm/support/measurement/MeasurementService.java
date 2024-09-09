package nl.athena.openehr.rm.support.measurement;

public interface MeasurementService {

    boolean isValidUnitsString(String units);
    boolean unitsEquivalent(String units1, String units2);

}
