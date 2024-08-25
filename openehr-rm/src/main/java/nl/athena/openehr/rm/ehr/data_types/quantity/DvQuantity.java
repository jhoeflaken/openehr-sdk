package nl.athena.openehr.rm.ehr.data_types.quantity;

public class DvQuantity extends DvAmount {

    private Float magnitude;
    private Integer precision;
    private String units;
    private String unitSystem;
    private String unitsDisplayName;

    public boolean isIntegral() {
        return false;
    }

}
