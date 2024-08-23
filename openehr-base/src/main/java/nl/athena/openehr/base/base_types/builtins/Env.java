package nl.athena.openehr.base.base_types.builtins;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

public interface Env {

    LocalDate currentDate();
    LocalTime currentTime();
    LocalDateTime currentDateTime();
    TimeZone currentTimeZone();

}
