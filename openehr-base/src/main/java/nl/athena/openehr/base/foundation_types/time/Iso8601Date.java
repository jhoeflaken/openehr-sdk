package nl.athena.openehr.base.foundation_types.time;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;

public class Iso8601Date extends Iso8601Type {

    private final int year;
    private final int month;
    private final int day;
    private final Iso8601Timezone timezone;

    /**
     * Constructor for new ISO-8601 date.
     *
     * @param theValue The ISO8601 value.
     */
    public Iso8601Date(@NotNull final String theValue) {
        this(theValue, null);
    }

    /**
     * Constructor for new ISO-8601 date.
     *
     * @param theValue The ISO-8601 date.
     * @param theTimezone The timezone.
     */
    public Iso8601Date(
            @NotNull String theValue,
            final Iso8601Timezone theTimezone) {
        super(theValue);
        timezone = theTimezone;

        final Matcher matcher = ISO_8601_DATE_PATTERN.matcher(theValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid ISO8601 date: " + theValue);
        }

        year = Integer.parseInt(matcher.group(1));
        month = matcher.group(3)  != null ? Integer.parseInt(matcher.group(3)) : 0;
        day = matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : 0;

        // If year (required), month (optional) and day (optional) is specified we need to
        // check if the day is valid.
        if (month != 0 && day != 0) {
            if (!validDay(year, month, day)) {
                throw new IllegalArgumentException("Invalid ISO8601 date: " + theValue);
            }
        }
    }

    /**
     * Extract the year part of the date as an Integer.
     *
     * @return Year part of the date as an Integer.
     */
    public int year() {
        return year;
    }

    /**
     * Extract the month part of the date as an Integer, or return 0 if not present.
     *
     * @return Month part of the date as an Integer, or return 0 if not present.
     */
    public int month() {
        return month;
    }

    /**
     * Extract the day part of the date as an Integer, or return 0 if not present.
     *
     * @return Day part of the date as an Integer, or return 0 if not present.
     */
    public int day() {
        return day;
    }

    /**
     * Extract the time zone part of the date as an Iso8601TimeZone.
     *
     * @return Time zone part of the date as an Iso8601TimeZone. Can be null.
     */
    public Iso8601Timezone timeZone() {
        return timezone;
    }

    /**
     * True if the month part of the date is unknown.
     *
     * @return True if the month part of the date is unknown.
     */
    public boolean monthUnknown() {
        return month == 0;
    }

    /**
     * True if the day part of the date is unknown.
     *
     * @return True if the day part of the date is unknown.
     */
    public boolean dayUnknown() {
        return day == 0;
    }

    /**
     * The extended form of the date, with '-' separators.
     *
     * @return The extended form of the date, with '-' separators.
     */
    public String asString() {
        if (month != 0 && day != 0) {
            return String.format("%04d-%02d-%02d", year, month, day);
        } else if (month != 0) {
            return String.format("%04d-%02d", year, month);
        } else {
            return String.format("%04d", year);
        }
    }

    /**
     * Add a duration to this date, returning a new date.
     *
     * @param duration The duration to add.
     * @return A new date with the duration added.
     */
    public Iso8601Date add(final Iso8601Duration duration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final Period period = Period.of(duration.years(), duration.months(), duration.days() + duration.weeks() * 7);
        final LocalDate newDate = currentDate.plus(period);

        return new Iso8601Date(newDate.toString(), timezone);
    }

    /**
     * Subtract a duration from this date, returning a new date.
     *
     * @param duration The duration to subtract.
     * @return A new date with the duration subtracted.
     */
    public Iso8601Date subtract(final Iso8601Duration duration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final Period period = Period.of(duration.years(), duration.months(), duration.days() + duration.weeks() * 7);
        final LocalDate newDate = currentDate.minus(period);

        return new Iso8601Date(newDate.toString(), timezone);
    }

    /**
     * Calculate the difference between this date and another date.
     *
     * @param other The other date.
     * @return The difference between this date and the other date.
     */
    public Iso8601Duration diff(final Iso8601Date other) {
        final LocalDate thisDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final LocalDate otherDate = LocalDate.of(other.year(), other.month() == 0 ? 1 : other.month(), other.day() == 0 ? 1 : other.day());
        final Period period = Period.between(otherDate, thisDate);

        StringBuilder duration = new StringBuilder("P");

        if (period.getYears() != 0) {
            duration.append(period.getYears()).append("Y");
        }
        if (period.getMonths() != 0) {
            duration.append(period.getMonths()).append("M");
        }
        if (period.getDays() != 0) {
            duration.append(period.getDays()).append("D");
        }

        return null;
    }

    /**
     * Add a nominal duration to this date, returning a new date. For example, a duration of 'P1Y' means advance to the
     * same date next year, except for the date 29 February in a leap year, to which the addition of a nominal year
     * will result in 28 February of the following year. Similarly, 'P1M' is understood here as a nominal  month, the
     * addition of which will result in one of:
     * <ul>
     *      <li>
     *          the same day in the following month, if it exists, or;
     *      </li>
     *      <li>
     *          one or two days less where the following month is shorter, or;
     *      </li>
     *      <li>
     *          in the case of adding a month to the date 31 Jan, the result will be 28 Feb in a non-leap year
     *          (i.e. three less) and 29 Feb in a leap year (i.e. two less).
     *      </li>
     * </ul>
     *
     * @param duration The nominal duration to add.
     * @return A new date with the nominal duration added.
     */
    public Iso8601Date addNominal(final Iso8601Duration duration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final LocalDate newDate = currentDate
                .plusYears(duration.years())
                .plusMonths(duration.months())
                .plusDays(duration.days() + duration.weeks() * 7L);

        return new Iso8601Date(newDate.toString(), timezone);
    }

    /**
     * Subtract a nominal duration to this date, returning a new date. See {@link #addNominal} for
     * details and semantics.
     *
     * @param duration The nominal duration to add.
     * @return A new date with the nominal duration added.
     */
    public Iso8601Date subtractNominal(final Iso8601Duration duration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final LocalDate newDate = currentDate
                .minusYears(duration.years())
                .minusMonths(duration.months())
                .minusDays(duration.days() + duration.weeks() * 7L);

        return new Iso8601Date(newDate.toString(), timezone);
    }

    @Override
    public boolean isPartial() {
        return monthUnknown() || dayUnknown();
    }

    @Override
    public boolean isExtended() {
        return getValue().contains("-");
    }


    @Override
    @SuppressWarnings("NullableProblems")
    public int compareTo(@NotNull Temporal theObject) {
        if (!(theObject instanceof Iso8601Date other)) {
            throw new IllegalArgumentException("Cannot compare Iso8601Date with " + theObject.getClass().getSimpleName());
        }

        if (year != other.year) {
            return year - other.year;
        }

        if (month != other.month) {
            return month - other.month;
        }

        if (day != other.day) {
            return day - other.day;
        }

        return 0;
    }

}
