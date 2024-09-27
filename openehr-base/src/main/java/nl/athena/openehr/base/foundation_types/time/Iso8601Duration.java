package nl.athena.openehr.base.foundation_types.time;

import jakarta.validation.constraints.NotNull;

import java.util.regex.Matcher;

/**
 * Represents an ISO 8601 duration, which may have multiple parts from years down to seconds. The value attribute is
 * a String in the format: [-]P[nnY][nnM][nnW][nnD][T[nnH][nnM][nnS]]. See
 * <a href="https://specifications.openehr.org/releases/BASE/Release-1.2.0/foundation_types.html#_iso8601_duration_class">
 * Iso8601Duration</a> class.
 */
public class Iso8601Duration extends Iso8601Type {

    private final int years;
    private final int months;
    private final int weeks;
    private final int days;
    private final int hours;
    private final int minutes;
    private final int seconds;
    private final float fractionalSeconds;
    private final boolean isDecimalSignComma;
    private final boolean isNegative;

    private float totalDurationInSeconds;

    /**
     * Pattern for ISO8601 duration.
     *
     * @param theValue The ISO8601 duration value.
     */
    public static Iso8601Duration of(@NotNull final String theValue) {
        final Matcher matcher = ISO_8601_DURATION_PATTERN.matcher(theValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid ISO8601 duration value: " + theValue);
        }

        int years = matcher.group(1) != null ? Integer.parseInt(matcher.group(1)) : 0;
        int months = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
        int weeks = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
        int days = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
        int hours = matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : 0;
        int minutes = matcher.group(6) != null ? Integer.parseInt(matcher.group(6)) : 0;
        int seconds = matcher.group(7) != null ? Integer.parseInt(matcher.group(7)) : 0;

        boolean isDecimalSignComma = false;
        float fractionalSeconds = 0.0f;
        String fraction = matcher.group(8);
        if (fraction != null) {
            if (fraction.contains(",")) {
                isDecimalSignComma = true;
                fraction = fraction.replace(",", ".");
            }

            fractionalSeconds = Float.parseFloat(fraction);
        }
        boolean isNegative = theValue.startsWith("-");

        return new Iso8601Duration(theValue, years, months, weeks, days, hours, minutes, seconds, fractionalSeconds,
                isDecimalSignComma, isNegative);
    }

    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue             The ISO8601 duration value.
     * @param theYears             The number of years.
     * @param theMonths            The number of months.
     * @param theWeeks             The number of weeks.
     * @param theDays              The number of days.
     * @param theHours             The number of hours.
     * @param theMinutes           The number of minutes.
     * @param theSeconds           The number of seconds.
     * @param theFractionalSeconds The number of fractional seconds.
     * @param theIsDecimalSignComma The decimal sign is comma.
     * @param theIsNegative         The duration is negative.
     */
    Iso8601Duration(
            final String theValue,
            final int theYears,
            final int theMonths,
            final int theWeeks,
            final int theDays,
            final int theHours,
            final int theMinutes,
            final int theSeconds,
            final float theFractionalSeconds,
            final boolean theIsDecimalSignComma,
            final boolean theIsNegative) {
        super(theValue);
        years = theYears;
        months = theMonths;
        weeks = theWeeks;
        days = theDays;
        hours = theHours;
        minutes = theMinutes;
        seconds = theSeconds;
        fractionalSeconds = theFractionalSeconds;
        isDecimalSignComma = theIsDecimalSignComma;
        isNegative = theIsNegative;

        long totalSeconds = 0;

        // Convert each component to seconds and add to totalSeconds
        totalSeconds += years * 365L * 24L * 60L * 60L; // Approximate conversion for years
        totalSeconds += months * 30L * 24L * 60L * 60L; // Approximate conversion for months
        totalSeconds += weeks * 7L * 24L * 60L * 60L;
        totalSeconds += days * 24L * 60L * 60L;
        totalSeconds += hours * 60L * 60L;
        totalSeconds += minutes * 60L;
        totalSeconds += seconds;

        // Add fractional seconds
        float total = totalSeconds + fractionalSeconds;

        // If the duration is negative, make the total duration negative
        totalDurationInSeconds = isNegative ? -total : total;
    }

    /**
     * Number of years in the value, i.e. the number preceding the 'Y' in the 'YMD' part, if one exists otherwise 0.
     *
     * @return The number of years.
     */
    public Integer years() {
        return isNegative ? -years : years;
    }

    /**
     * Number of months in the value, i.e. the number preceding the 'M' in the 'YMD' part, if one exists otherwise 0.
     *
     * @return The number of months.
     */
    public Integer months() {
        return isNegative ? -months : months;
    }

    /**
     * Number of weeks in the value, i.e. the number preceding the 'W' in the 'W' part, if one exists otherwise 0.
     *
     * @return The number of weeks.
     */
    public Integer weeks() {
        return isNegative ? -weeks : weeks;
    }

    /**
     * Number of days in the value, i.e. the number preceding the 'D' in the 'YMD' part, if one exists otherwise 0.
     *
     * @return The number of days.
     */
    public Integer days() {
        return isNegative ? -days : days;
    }

    /**
     * Number of hours in the value, i.e. the number preceding the 'H' in the 'HMS' part, if one exists otherwise 0.
     *
     * @return The number of hours.
     */
    public Integer hours() {
        return isNegative ? -hours : hours;
    }

    /**
     * Number of minutes in the value, i.e. the number preceding the 'M' in the 'HMS' part, if one exists otherwise 0.
     *
     * @return The number of minutes.
     */
    public Integer minutes() {
        return isNegative ? -minutes : minutes;
    }

    /**
     * Number of seconds in the value, i.e. the number preceding the 'S' in the 'HMS' part, if one exists otherwise 0.
     *
     * @return The number of seconds.
     */
    public Integer seconds() {
        return isNegative ? -seconds : seconds;
    }

    /**
     * Fractional seconds in the value, i.e. the number following the 'S' in the 'HMS' part, if one exists otherwise 0.
     *
     * @return The number of fractional seconds.
     */
    public Float fractionalSeconds() {
        return isNegative ? -fractionalSeconds : fractionalSeconds;
    }

    /**
     * Returns true if the decimal sign is a comma.
     *
     * @return True if the decimal sign is a comma.
     */
    public boolean isDecimalSignComma() {
        return isDecimalSignComma;
    }

    /**
     * The duration in seconds.
     *
     * @return The duration in seconds.
     */
    public Float toSeconds() {
        return totalDurationInSeconds;
    }

    /**
     * Get the duration in extended format.
     *
     * @return The duration in extended format.
     */
    public String asString() {
        return getValue();
    }

    public Iso8601Duration add(final Iso8601Duration theOther) {
        return null;
    }

    public Iso8601Duration subtract(final Iso8601Duration theOther) {
        return null;
    }

    public Iso8601Duration multiply(final Float theFactor) {
        return null;
    }

    public Iso8601Duration divide(final Float theDivisor) {
        return null;
    }

    public Iso8601Duration negative() {
        return null;
    }

    @Override
    public boolean isPartial() {
        return false;
    }

    @Override
    public boolean isExtended() {
        return true;
    }

    @Override
    public int compareTo(Temporal o) {
        return 0;
    }
}
