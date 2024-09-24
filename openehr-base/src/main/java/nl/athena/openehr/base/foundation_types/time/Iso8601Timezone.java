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
    public Iso8601Timezone(@NotNull final String theValue) {
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

    public int hour() {
        return hour;
    }

    public int minute() {
        return minute;
    }

    public int sign() {
        return sign;
    }

    public boolean minuteUnknown() {
        return minuteUnknown;
    }

    @Override
    public boolean isPartial() {
        return isPartial;
    }

    @Override
    public boolean isExtended() {
        return isExtended;
    }

    public boolean isGmt() {
        return hour == 0 && minute == 0;
    }

    public boolean isUtc() {
        return isGmt();
    }

    public String asString() {
        final String s = sign == 1 ? "+" : "-";
        return String.format("%s%02d:%02d", s, hour, minute);
    }

}
