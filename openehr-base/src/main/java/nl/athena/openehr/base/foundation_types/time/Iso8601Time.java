package nl.athena.openehr.base.foundation_types.time;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.Duration;
import java.time.LocalTime;
import java.util.regex.Matcher;

/**
 * Represents an ISO 8601 time, including partial and extended forms. Value may be:
 * <ul>
 *  <li>hh:mm:ss[(,|.)sss][Z|±hh[:mm]] (extended, preferred) or</li>
 *  <li>hhmmss[(,|.)sss][Z|±hh[mm]] (compact)</li>
 *  <li>or a partial invariant.</li>
 * </ul>
 * See {@link TimeDefinitions#validIso8601Time} for validity.
 */
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Iso8601Time")
public class Iso8601Time extends Iso8601Type implements Comparable<Iso8601Time> {

    @JsonIgnore
    @XmlTransient
    private final int hours;

    @JsonIgnore
    @XmlTransient
    private final int minutes;

    @JsonIgnore
    @XmlTransient
    private final int seconds;

    @JsonIgnore
    @XmlTransient
    private final float fractionalSeconds;

    @JsonIgnore
    @XmlTransient
    private final Iso8601Timezone timezone;

    @JsonIgnore
    @XmlTransient
    private final boolean minuteUnknown;

    @JsonIgnore
    @XmlTransient
    private final boolean secondUnknown;

    @JsonIgnore
    @XmlTransient
    private final boolean isDecimalSignComma;

    @JsonIgnore
    @XmlTransient
    private final boolean hasFractionalSeconds;

    @JsonIgnore
    @XmlTransient
    private final boolean isPartial;

    @JsonIgnore
    @XmlTransient
    private final boolean isExtended;

    /**
     * Create an instance of Iso8601Time.
     *
     * @param theValue The ISO8601 time value.
     * @return The instance of Iso8601Time.
     */
    public static Iso8601Time of(@NotNull final String theValue) {
        return of(theValue, null);
    }

    /**
     * Create an instance of Iso8601Time.
     *
     * @param theValue The ISO8601 time value.
     * @param theTimezone The timezone.
     * @return The instance of Iso8601Time.
     */
    public static Iso8601Time of(
            @NotNull final String theValue,
            final Iso8601Timezone theTimezone) {
        final Matcher matcher = TimeDefinitions.ISO_8601_TIME_PATTERN.matcher(theValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid ISO8601 time: " + theValue);
        }

        int hours = Integer.parseInt(matcher.group(1));
        int minutes = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
        int seconds = matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : 0;
        float fractionalSeconds = matcher.group(6) != null ? Float.parseFloat(matcher.group(6)) : 0.0f;
        boolean isDecimalSignComma = matcher.group(6) != null && matcher.group(6).startsWith(",");
        boolean hasFractionalSeconds = matcher.group(6) != null;
        boolean minuteUnknown = matcher.group(3) == null;
        boolean secondUnknown = matcher.group(5) == null;
        boolean isPartial = minuteUnknown || secondUnknown;
        boolean isExtended = theValue.contains(":");

        return new Iso8601Time(theValue, hours, minutes, seconds, fractionalSeconds, isDecimalSignComma,
                hasFractionalSeconds, minuteUnknown, secondUnknown, isPartial, isExtended, theTimezone);
    }

    /**
     * Create an instance of Iso8601Time.
     *
     * @param theValue                The ISO8601 time value.
     * @param theHours                The hours.
     * @param theMinutes              The minutes.
     * @param theSeconds              The seconds.
     * @param theFractionalSeconds    The fractional seconds.
     * @param theIsDecimalSignComma   The decimal sign is a comma.
     * @param theHasFractionalSeconds The time has fractional seconds.
     * @param theMinuteUnknown        The minute is unknown.
     * @param theSecondUnknown        The second is unknown.
     * @param theIsPartial            The time is partial.
     * @param theIsExtended           The time is extended.
     * @param theTimezone             The timezone.
     */
    public Iso8601Time(
            final String theValue,
            int theHours,
            int theMinutes,
            int theSeconds,
            float theFractionalSeconds,
            boolean theIsDecimalSignComma,
            boolean theHasFractionalSeconds,
            boolean theMinuteUnknown,
            boolean theSecondUnknown,
            boolean theIsPartial,
            boolean theIsExtended, Iso8601Timezone theTimezone) {
        super(theValue);
        hours = theHours;
        minutes = theMinutes;
        seconds = theSeconds;
        fractionalSeconds = theFractionalSeconds;
        isDecimalSignComma = theIsDecimalSignComma;
        hasFractionalSeconds = theHasFractionalSeconds;
        minuteUnknown = theMinuteUnknown;
        secondUnknown = theSecondUnknown;
        isPartial = theIsPartial;
        isExtended = theIsExtended;
        timezone = theTimezone;
    }

    /**
     * Get the hours.
     *
     * @return The hours.
     */
    public int hours() {
        return hours;
    }

    /**
     * Get the minutes, returns 0 if unknown.
     *
     * @return The minutes.
     */
    public int minutes() {
        return minutes;
    }

    /**
     * Get the seconds, returns 0 if unknown.
     *
     * @return The seconds.
     */
    public int seconds() {
        return seconds;
    }

    /**
     * Get the fractional seconds, returns 0.0 if unknown.
     *
     * @return The fractional seconds.
     */
    public float fractionalSeconds() {
        return fractionalSeconds;
    }

    /**
     * Get the timezone. Can be null.
     *
     * @return The timezone.
     */
    @Null
    public Iso8601Timezone timezone() {
        return timezone;
    }

    /**
     * Check if the minute is unknown.
     *
     * @return True if the minute is unknown, false otherwise.
     */
    public boolean minuteUnknown() {
        return minuteUnknown;
    }

    /**
     * Check if the second is unknown.
     *
     * @return True if the second is unknown, false otherwise.
     */
    public boolean secondUnknown() {
        return secondUnknown;
    }

    /**
     * Check if the time has fractional seconds.
     *
     * @return True if the time has fractional seconds, false otherwise.
     */
    public boolean hasFractionalSeconds() {
        return hasFractionalSeconds;
    }

    @Override
    public boolean isPartial() {
        return isPartial;
    }

    @Override
    public boolean isExtended() {
        return isExtended;
    }

    /**
     * Get the time as a string in extended format.
     * .
     * @return The time as a string in extended format.
     */
    public String asString() {
        final StringBuilder timeString = new StringBuilder(String.format("%02d:%02d:%02d", hours, minutes, seconds));

        if (hasFractionalSeconds) {
            timeString.append(String.format(isDecimalSignComma ? ",%03d" : ".%03d", (int) (fractionalSeconds * 1000)));
        }

        if (timezone != null) {
            timeString.append(timezone.asString());
        }

        return timeString.toString();
    }

    @Override
    protected int compareTo(@Nonnull Temporal theOther) {
        if (!(theOther instanceof Iso8601Time other)) {
            throw new IllegalArgumentException("Can only compare with another Iso8601Time instance");
        }

        LocalTime thisTime = LocalTime.of(hours, minutes, seconds, (int) (fractionalSeconds * 1_000_000_000));
        LocalTime thatTime = LocalTime.of(other.hours(), other.minutes(), other.seconds(), (int) (other.fractionalSeconds() * 1_000_000_000));

        if (timezone != null) {
            thisTime = thisTime
                    .minusHours(timezone.hour())
                    .minusMinutes(timezone.minute());
        }

        if (other.timezone() != null) {
            thatTime = thatTime
                    .minusHours(other.timezone().hour())
                    .minusMinutes(other.timezone().minute());
        }

        return thisTime.compareTo(thatTime);
    }

    public Iso8601Time add(Iso8601Duration theDuration) {
        LocalTime currentTime = LocalTime.of(hours, minutes, seconds, (int) (fractionalSeconds * 1_000_000_000));
        LocalTime newTime = currentTime.plusHours(theDuration.hours())
                .plusMinutes(theDuration.minutes())
                .plusSeconds(theDuration.seconds())
                .plusNanos((long) (theDuration.fractionalSeconds() * 1_000_000_000));

        String value = newTime.toString();
        if (hasFractionalSeconds) {
            value += isDecimalSignComma ? "," : ".";
            value += String.format("%03d", (int) (fractionalSeconds * 1000));
        }

        return new Iso8601Time(value, newTime.getHour(), newTime.getMinute(), newTime.getSecond(),
                newTime.getNano() / 1_000_000_000.0f, isDecimalSignComma, hasFractionalSeconds,
                minuteUnknown, secondUnknown, isPartial, isExtended, timezone);
    }

    public Iso8601Time subtract(Iso8601Duration theDuration) {
        LocalTime currentTime = LocalTime.of(hours, minutes, seconds, (int) (fractionalSeconds * 1_000_000_000));
        LocalTime newTime = currentTime.minusHours(theDuration.hours())
                .minusMinutes(theDuration.minutes())
                .minusSeconds(theDuration.seconds())
                .minusNanos((long) (theDuration.fractionalSeconds() * 1_000_000_000));

        String value = newTime.toString();
        if (hasFractionalSeconds) {
            value += isDecimalSignComma ? "," : ".";
            value += String.format("%03d", (int) (fractionalSeconds * 1000));
        }

        return new Iso8601Time(value, newTime.getHour(), newTime.getMinute(), newTime.getSecond(),
                newTime.getNano() / 1_000_000_000.0f, isDecimalSignComma, hasFractionalSeconds,
                minuteUnknown, secondUnknown, isPartial, isExtended, timezone);
    }

    public Iso8601Duration diff(Iso8601Time theOther) {
        LocalTime thisTime = LocalTime.of(hours, minutes, seconds, (int) (fractionalSeconds * 1_000_000_000));
        LocalTime otherTime = LocalTime.of(theOther.hours(), theOther.minutes(), theOther.seconds(), (int) (theOther.fractionalSeconds() * 1_000_000_000));

        if (timezone != null) {
            thisTime = thisTime
                    .minusHours(timezone.hour())
                    .minusMinutes(timezone.minute());
        }

        if (theOther.timezone() != null) {
            otherTime = otherTime
                    .minusHours(theOther.timezone().hour())
                    .minusMinutes(theOther.timezone().minute());
        }

        Duration duration = Duration.between(otherTime, thisTime);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        float fractionalSeconds = duration.getNano() / 1_000_000_000.0f;

        String value = String.format("PT%dH%dM%dS", hours, minutes, seconds);
        if (fractionalSeconds > 0) {
            value += String.format(".%03d", (int) (fractionalSeconds * 1000));
        }

        return new Iso8601Duration(value, 0, 0, 0, 0, (int) hours, (int) minutes,
                (int) seconds, fractionalSeconds,  isDecimalSignComma, duration.isNegative());
    }

    @Override
    public int compareTo(@Nonnull Iso8601Time theOther) {
        return compareTo((Temporal) theOther);
    }

    @JsonProperty("_type")
    public String getType() {
        return "TIME";
    }
}
