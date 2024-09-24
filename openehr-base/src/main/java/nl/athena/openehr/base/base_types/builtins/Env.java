package nl.athena.openehr.base.base_types.builtins;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.TimeZone;

/**
 * Class representing the real-world environment, providing basic information like current time, date, etc. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/base_types.html#_env_interface">
 *     Env</a> interface.
 */
public interface Env {

    /**
     * Return today’s date in the current locale.
     *
     * @return Today’s date in the current locale.
     */
    LocalDate currentDate();

    /**
     * Return the current time in the current locale.
     *
     * @return The current time in the current locale.
     */
    LocalTime currentTime();

    /**
     * Return the current date and time in the current locale.
     *
     * @return The current date and time in the current locale.
     */
    LocalDateTime currentDateTime();

    /**
     * Return the time zone of the current locale.
     *
     * @return The time zone of the current locale.
     */
    TimeZone currentTimeZone();

}
