package nl.athena.openehr.base.foundation_types.time;

import java.time.YearMonth;
import java.util.regex.Pattern;

/**
 * Definitions for date/time classes. Note that the timezone limits are set by where the international dateline is.
 * Thus, time in New Zealand is quoted using +12:00, not -12:00. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_time_definitions_class">
 *     TimeDefinitions</a> interface.
 */
public interface TimeDefinitions {

    int SECONDS_IN_MINUTE = 60;
    int MINUTES_IN_HOUR = 60;
    int HOURS_IN_DAY = 24;
    float AVERAGE_DAYS_IN_MONTH = 30.42f;
    int MAX_DAYS_IN_MONTH = 31;
    int DAYS_IN_YEAR = 365;
    float AVERAGE_DAYS_IN_YEAR = 365.24f;
    int DAYS_IN_LEAP_YEAR = 366;
    int MAX_DAYS_IN_YEAR = DAYS_IN_LEAP_YEAR;
    int DAYS_IN_WEEK = 7;
    int MONTHS_IN_YEAR = 12;
    int MIN_TIMEZONE_HOUR = 12;
    int MAX_TIMEZONE_HOUR = 14;
    float NOMINAL_DAYS_IN_MONTH = AVERAGE_DAYS_IN_MONTH;
    float NOMINAL_DAYS_IN_YEAR = AVERAGE_DAYS_IN_YEAR;

    default boolean validYear(int theYear) {
        return theYear >= 0;
    }

    default boolean validMonth(int theMonth) {
        return theMonth >= 1 && theMonth <= MONTHS_IN_YEAR;
    }

    default boolean validDay(int theYear, int theMonth, int theDay) {
        final int daysInMonth = YearMonth.of(theYear, theMonth).lengthOfMonth();
        return theDay >= 1 && theDay <= daysInMonth;
    }

    default boolean validHour(int theHour, int theMinute, int theSecond) {
        return (theHour >= 0 && theHour < HOURS_IN_DAY) || (theHour == HOURS_IN_DAY && theMinute == 0 && theSecond == 0);
    }

    default boolean validMinute(int theMinute) {
        return theMinute >= 0 && theMinute < MINUTES_IN_HOUR;
    }

    default boolean validSecond(int theSecond) {
        return theSecond >= 0 && theSecond < SECONDS_IN_MINUTE;
    }

    default boolean validFractionalSecond(float theFractionalSecond) {
        return theFractionalSecond >= 0.0 && theFractionalSecond < 1.0;
    }

    default boolean validIso8601DDate(final String theIso8601Date) {
        return true;
    }

    default boolean validIso8601Time(final String theIso8601Time) {
        return true;
    }

    default boolean validIso8601DateTime(final String theIso8601DateTime) {
        return true;
    }

    default boolean validIso8601Duration(final String theIso8601Duration) {
        return true;
    }

}
