package nl.athena.openehr.rm.demopgraphic;

import nl.athena.openehr.rm.data_types.text.DvText;

import java.util.List;

public abstract class Actor extends Party {

    private List<DvText> languages;
    private List<Role> roles;

}
