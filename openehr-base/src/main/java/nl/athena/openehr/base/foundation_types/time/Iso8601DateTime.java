package nl.athena.openehr.base.foundation_types.time;

import java.time.YearMonth;
import java.util.regex.Matcher;

/**
 *
 * Represents an ISO 8601 date/time, including partial and extended forms. Value may be:
 * <br/>
 * <ul>
 *     <li>YYYY-MM-DDThh:mm:ss[(,|.)sss][Z | ±hh[:mm]] (extended, preferred) or</li>
 *     <li>YYYYMMDDThhmmss[(,|.)sss][Z | ±hh[mm]] (compact)</li>
 *     <li>or a partial variant.</li>
 * </ul>
 * <br/>
 * See {@link TimeDefinitions#validIso8601DateTime} for validity.
 * <br/><br/>
 * Note that this class includes 2 deviations from ISO 8601:2004:
 * <br/><br/>
 * <ul>
 *      <li>for partial date/times, any part of the date/time up to the month may be missing, not just seconds and
 *      minutes as in the standard;</li>
 *      <li>the time 24:00:00 is not allowed, since it would mean the date was really on the next day.</li>
 * </ul>
 * <br/>
 * See <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_iso8601_date_time_class">
 *     Iso8601DateTime</a> class.
 */
public class Iso8601DateTime extends Iso8601Type {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private float fractionalSecond;
    private Iso8601Timezone timezone;

    public static Iso8601DateTime of(String theValue) {
        final Matcher matcher = ISO_8601_DATE_TIME_PATTERN.matcher(theValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid ISO8601 date-time value: " + theValue);
        }

        int year = Integer.parseInt(matcher.group(1));
        int month = matcher.group(3)  != null ? Integer.parseInt(matcher.group(3)) : -1;
        int day = matcher.group(6) != null ? Integer.parseInt(matcher.group(5)) : -1;

        // If year (required), month (optional) and day (optional) is specified we need to
        // check if the day is valid.
        if (month != 0 && day != 0) {
            final int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
            if (day <= 0 || day > daysInMonth) {
                throw new IllegalArgumentException("Invalid ISO8601 date/time: " + theValue);
            }
        }

        int hour = matcher.group(8) != null ?  Integer.parseInt(matcher.group(8)) : 0;
        int minute = matcher.group(10) != null ? Integer.parseInt(matcher.group(10)) : 0;
        int second = matcher.group(12) != null ? Integer.parseInt(matcher.group(12)) : 0;
        float fractionalSecond = matcher.group(14) != null ? Float.parseFloat(matcher.group(14)) : 0.0f;

        String timeZone = matcher.group(16);
        Iso8601Timezone iso8601Timezone = timeZone != null ? Iso8601Timezone.of(timeZone) : null;


        return new Iso8601DateTime(theValue, year, month, day, hour, minute, second, fractionalSecond, iso8601Timezone);
    }

    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601DateTime(
            final String theValue,
            final int theYear,
            final int theMonth,
            final int theDay,
            final int theHour,
            final int theMinute,
            final int theSecond,
            final float theFractionalSecond,
            final Iso8601Timezone theTimeZone) {
        super(theValue);
        year = theYear;
        month = theMonth;
        day = theDay;
        hour = theHour;
        minute = theMinute;
        second = theSecond;
        fractionalSecond = theFractionalSecond;
        timezone = theTimeZone;
    }

    public int year() {
        return year;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }

    public int second() {
        return second;
    }

    public float fractionalSecond() {
        return fractionalSecond;
    }

    public Iso8601Timezone timezone() {
        return timezone;
    }

    public boolean monthUnknown() {
        return month == 0;
    }

    public boolean dayUnknown() {
        return day == 0;
    }

    public boolean minuteUnknown() {
        return minute == 0;
    }

    public boolean secondUnknown() {
        return second == 0;
    }

    public boolean isDecimalSignComma() {
        return false;
    }

    public boolean hasFractionalSeconds() {
        return fractionalSecond != 0.0f;
    }

    @Override
    public boolean isPartial() {
        return false;
    }

    @Override
    public boolean isExtended() {
        return false;
    }

    @Override
    public int compareTo(Temporal o) {
        return 0;
    }

    public Iso8601DateTime add(final Iso8601Duration theDuration) {
        return null;
    }

    public Iso8601Duration subtract(final Iso8601DateTime theDateTime) {
        return null;
    }

    public Iso8601Duration diff(final Iso8601DateTime theDateTime) {
        return null;
    }

    public Iso8601DateTime addNominal(final Iso8601Duration theDuration) {
        return null;
    }

    public Iso8601Duration subtractNominal(final Iso8601DateTime theDateTime) {
        return null;
    }

    public String asString() {
        return null;
    }

}
