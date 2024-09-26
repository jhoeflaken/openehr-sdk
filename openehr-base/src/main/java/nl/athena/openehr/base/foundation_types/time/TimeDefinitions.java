package nl.athena.openehr.base.foundation_types.time;

import java.time.YearMonth;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Definitions for date/time classes. Note that the timezone limits are set by where the international dateline is.
 * Thus, time in New Zealand is quoted using +12:00, not -12:00. See
 * <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_time_definitions_class">
 *     TimeDefinitions</a> interface.
 */
public interface TimeDefinitions {

    // ISO8601 date pattern.
    String ISO_8601_DATE_REGEX = "^(\\d{4})(-?(0[1-9]|1[0-2])(-?(0[1-9]|[12]\\d|3[01]))?)?$";
    Pattern ISO_8601_DATE_PATTERN = Pattern.compile(ISO_8601_DATE_REGEX);

    // ISO8601 time pattern.
    String ISO_8601_TIME_REGEX = "^([01]\\d|2[0-3])(:?([0-5]\\d)(:?([0-5]\\d)([.,]\\d{1,6})?)?)?" +
            "(Z|([+-])([01]\\d|2[0-3])(:?([0-5]\\d)?)?)?$";
    Pattern ISO_8601_TIME_PATTERN = Pattern.compile(ISO_8601_TIME_REGEX);

    // ISO8601 date-time pattern.
    String ISO_8601_DATE_TIME_REGEX = "^(\\d{4})(-?)(0[1-9]|1[0-2])(-?)(0[1-9]|[12]\\d|3[01])T([01]\\d|2[0-3])" +
            "(:?([0-5]\\d)(:?([0-5]\\d)([.,]\\d{1,6})?)?)?(Z|([+-])([01]\\d|2[0-3])(:?([0-5]\\d)?)?)?$";
    Pattern ISO_8601_DATE_TIME_PATTERN = Pattern.compile(ISO_8601_DATE_TIME_REGEX);

    // ISO8601 duration pattern.
    String ISO_8601_DURATION_REGEX = "^P(([0-9]+)Y)?(([0-9]+)M)?(([0-9]+)W)?(([0-9]+)D)?" +
            "(T(([0-9]+)H)?(([0-9]+)M)?(([0-9]+)([.,][0-9]{0,6})?S)?)?$";
    Pattern ISO_8601_DURATION_PATTERN = Pattern.compile(ISO_8601_DURATION_REGEX);

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

    default boolean validIso8601Date(final String theIso8601Date) {
        final Matcher matcher = ISO_8601_DATE_PATTERN.matcher(theIso8601Date);
        if (!matcher.matches()) {
            return false;
        }

        int year = Integer.parseInt(matcher.group(1));
        int month = matcher.group(3)  != null ? Integer.parseInt(matcher.group(3)) : -1;
        int day = matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : -1;

        // If year (required), month (optional) and day (optional) is specified we need to
        // check if the day is valid.
        if (month != -1 && day != -1) {
            return validDay(year, month, day);
        }

        return true;
    }

    default boolean validIso8601Time(final String theIso8601Time) {
        return ISO_8601_TIME_PATTERN.matcher(theIso8601Time).matches();
    }

    default boolean validIso8601DateTime(final String theIso8601DateTime) {
        final Matcher matcher = ISO_8601_DATE_TIME_PATTERN.matcher(theIso8601DateTime);
        if (!matcher.matches()) {
            return false;
        }

        int year = Integer.parseInt(matcher.group(1));
        int month = matcher.group(3)  != null ? Integer.parseInt(matcher.group(3)) : -1;
        int day = matcher.group(6) != null ? Integer.parseInt(matcher.group(5)) : -1;

        // If year (required), month (optional) and day (optional) is specified we need to
        // check if the day is valid.
        if (month != -1 && day != -1) {
            return validDay(year, month, day);
        }

        return true;
    }

    default boolean validIso8601Duration(final String theIso8601Duration) {
        return ISO_8601_DURATION_PATTERN.matcher(theIso8601Duration).matches();
    }

}
