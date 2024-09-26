package nl.athena.openehr.base.foundation_types.time;

import jakarta.validation.constraints.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ISO8601 timezone string, in format:
 *<p/>
 * Z | ±hh[mm]
 *<p/>
 * where:
 *<ul>
 *  <li>
 *      hh is "00" - "23" (0-filled to two digits)
 *  </li>
 *  <li>
 *      mm is "00" - "59" (0-filled to two digits)
 *  </li>
 *  <li>
 *      Z is a literal meaning UTC (modern replacement for GMT), i.e. timezone +0000
 *  </l>
 *  </ul>
 *  <p/>
 *  See <a href="https://specifications.openehr.org/releases/BASE/development/foundation_types.html#_iso8601_timezone_class">
 *      Iso8601Timezone</a> class.
 */
public class Iso8601Timezone extends Iso8601Type {

    private static final String ISO_8601_TIMEZONE_REGEX = "^(Z|([+-])(\\d{2})(:?\\d{2})?)$";
    private static final Pattern ISO_8601_TIMEZONE_PATTERN = Pattern.compile(ISO_8601_TIMEZONE_REGEX);

    private final int hour;
    private final int minute;
    private final int sign;
    private boolean minuteUnknown = true;
    private boolean isPartial = false;
    private boolean isExtended = false;

    /**
     * Constructor for Iso8601Type.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601Timezone(@NotNull String theValue) {
        super(theValue);

        final Matcher matcher = ISO_8601_TIMEZONE_PATTERN.matcher(theValue);
        if (matcher.matches()) {
            if (matcher.group(1).equals("Z")) {
                // UTC time which has offset +00:00
                hour = 0;
                minute = 0;
                sign = 1;
                minuteUnknown = false;
            } else {
                // Specified offset
                sign = matcher.group(2).equals("+") ? 1 : -1;
                hour = Integer.parseInt(matcher.group(3));

                // Check if minutes are specified.
                if (matcher.group(4) == null) {
                    minute = 0;
                    isPartial = true;
                } else {
                    minuteUnknown = false;
                    if (matcher.group(4).startsWith(":")) {
                        isExtended = true;
                        minute = Integer.parseInt(matcher.group(4).substring(1));
                    } else {
                        minute = Integer.parseInt(matcher.group(4));
                    }
                }
            }
        }  else {
            // Does not match the pattern, so not a valid timezone.
            throw new IllegalArgumentException("Invalid ISO8601 timezone value: " + theValue);
        }
    }

    /**
     * Extract the hour part of timezone, as an Integer in the range 00 - 24.
     * @return The hour part of timezone.
     */
    public int hour() {
        return hour;
    }

    /**
     * Extract the minute part of timezone, as an Integer in the range 00 - 59. Usually either 0 or 30.
     *
     * @return The minute part of timezone.
     */
    public int minute() {
        return minute;
    }

    /**
     * Extract the sign of the timezone, as an Integer as -1 or 1.
     *
     * @return The sign of the timezone.
     */
    public int sign() {
        return sign;
    }

    /**
     * True if the minute part of the timezone is unknown.
     *
     * @return True if the minute part of the timezone is unknown.
     */
    public boolean minuteUnknown() {
        return minuteUnknown;
    }

    /**
     * True if this timezone is partial, i.e. if minute part is missing.
     *
     * @return True if this timezone is partial.
     */
    @Override
    public boolean isPartial() {
        return isPartial;
    }

    /**
     * True if this timezone is in the 'extended' form, i.e. uses ':' separator.
     *
     * @return True if this timezone is in the 'extended' form.
     */
    @Override
    public boolean isExtended() {
        return isExtended;
    }

    /**
     * True if this timezone is UTC.
     *
     * @return True if this timezone is UTC.
     */
    public boolean isGmt() {
        return hour == 0 && minute == 0;
    }

    /**
     * True if this timezone is UTC.
     *
     * @return True if this timezone is UTC.
     */
    public boolean isUtc() {
        return isGmt();
    }

    /**
     * Return timezone string in extended format.
     *
     * @return The timezone string in extended format.
     */
    public String asString() {
        final String s = sign == 1 ? "+" : "-";
        return String.format("%s%02d:%02d", s, hour, minute);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public int compareTo(@NotNull Temporal theOther) {
        // Check if the other object is an instance of Iso8601Timezone.
        if (!(theOther instanceof Iso8601Timezone other)) {
            throw new IllegalArgumentException("Cannot compare Iso8601Timezone with " +
                    theOther.getClass().getSimpleName());
        }

        // Cast the other object to Iso8601Timezone and compare the values.
        if (sign != other.sign) {
            return sign - other.sign;
        }

        if (hour != other.hour) {
            return hour - other.hour;
        }

        return minute - other.minute;

    }

}
