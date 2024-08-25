package nl.athena.openehr.rm.common.archetyped;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public interface Pathable {

    Pathable parent();
    Object itemAtPath(@NotNull final String thePath);
    List<Object> itemsAtPath(@NotNull final String thePath);
    boolean pathExists(@NotNull final String thePath);
    boolean pathUnique(@NotNull final String thePath);
    String pathOfItem(@NotNull final Object theItem);

}
