package nl.athena.openehr.base.foundation_types.time;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.regex.Matcher;

/**
 * Represents an ISO 8601 date, including partial and extended forms. Value may be:
 * <ul>
 *  <li>YYYY-MM-DD (extended, preferred)</li>
 * * <li>YYYYMMDD (compact)</li>
 * <li>a partial invariant.</li>
 * </ul>
 * See {@link TimeDefinitions#validIso8601Date} for validity.
 */
@SuperBuilder(setterPrefix = "with", toBuilder = true)
@Jacksonized
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Iso8601Date")
public class Iso8601Date extends Iso8601Type implements Comparable<Iso8601Date> {

    @JsonIgnore
    @XmlTransient
    private final int year;

    @JsonIgnore
    @XmlTransient
    private final int month;

    @JsonIgnore
    @XmlTransient
    private final int day;

    @JsonIgnore
    @XmlTransient
    private final Iso8601Timezone timezone;

    /**
     * Create a new ISO-8601 date without timezone.
     *
     * @param theValue The ISO8601 date value.
     * @return A new Iso8601Date instance.
     */
    public static Iso8601Date of(@NotNull final String theValue) {
        return Iso8601Date.of(theValue, null);
    }

    /**
     * Create a new ISO-8601 date with timezone.
     *
     * @param theValue    The ISO8601 date value.
     * @param theTimezone The timezone.
     * @return A new Iso8601Date instance.
     */
    public static Iso8601Date of(
            @NotNull final String theValue,
            final Iso8601Timezone theTimezone) {

        final Matcher matcher = ISO_8601_DATE_PATTERN.matcher(theValue);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid ISO8601 date: " + theValue);
        }

        int year = Integer.parseInt(matcher.group(1));
        int month = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;
        int day = matcher.group(5) != null ? Integer.parseInt(matcher.group(5)) : 0;

        // If year (required), month (optional) and day (optional) is specified we need to
        // check if the day is valid.
        if (month != 0 && day != 0) {
            final int daysInMonth = YearMonth.of(year, month).lengthOfMonth();
            if (day <= 0 || day > daysInMonth) {
                throw new IllegalArgumentException("Invalid ISO8601 date: " + theValue);
            }
        }

        return new Iso8601Date(theValue, theTimezone, year, month, day);
    }

    /**
     * Constructor for new ISO-8601 date.
     *
     * @param theValue    The ISO8601 value.
     * @param theTimezone The timezone.
     * @param theYear     The year.
     * @param theMonth    The month.
     * @param theDay      The day.
     */
    public Iso8601Date(
            @NotNull final String theValue,
            final Iso8601Timezone theTimezone,
            final int theYear,
            final int theMonth,
            final int theDay) {
        super(theValue);
        timezone = theTimezone;
        year = theYear;
        month = theMonth;
        day = theDay;
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
     * @param theDuration The duration to add.
     * @return A new date with the duration added.
     */
    public Iso8601Date add(final Iso8601Duration theDuration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final Period period = Period.of(theDuration.years(), theDuration.months(), theDuration.days() + theDuration.weeks() * 7);
        final LocalDate newDate = currentDate.plus(period);
        final String value = String.format("%04d-%02d-%02d", newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());

        return new Iso8601Date(value, timezone, newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
    }

    /**
     * Subtract a duration from this date, returning a new date.
     *
     * @param theDuration The duration to subtract.
     * @return A new date with the duration subtracted.
     */
    public Iso8601Date subtract(final Iso8601Duration theDuration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final Period period = Period.of(theDuration.years(), theDuration.months(), theDuration.days() + theDuration.weeks() * 7);
        final LocalDate newDate = currentDate.minus(period);
        final String value = String.format("%04d-%02d-%02d", newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());

        return new Iso8601Date(value, timezone, newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
    }

    /**
     * Calculate the difference between this date and another date.
     *
     * @param theOther The other date.
     * @return The difference between this date and the other date.
     */
    public Iso8601Duration diff(final Iso8601Date theOther) {
        LocalDate thisDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        LocalDate otherDate = LocalDate.of(theOther.year(), theOther.month() == 0 ? 1 : theOther.month(), theOther.day() == 0 ? 1 : theOther.day());

        Period period = Period.between(otherDate, thisDate);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        final String value = String.format("P%dY%dM%dD", years, months, days);
        return new Iso8601Duration(value, years, months, 0, days, 0, 0, 0,
                0.0f, false, false);
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
     * @param theDuration The nominal duration to add.
     * @return A new date with the nominal duration added.
     */
    public Iso8601Date addNominal(final Iso8601Duration theDuration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final LocalDate newDate = currentDate.plusYears(theDuration.years())
                .plusMonths(theDuration.months())
                .plusWeeks(theDuration.weeks())
                .plusDays(theDuration.days());

        String value = String.format("%04d-%02d-%02d", newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
        return new Iso8601Date(value, timezone, newDate.getYear(), monthUnknown() ? 0 : newDate.getMonthValue(),
                dayUnknown() ? 0 : newDate.getDayOfMonth());
    }

    /**
     * Subtract a nominal duration to this date, returning a new date. See {@link #addNominal} for
     * details and semantics.
     *
     * @param theDuration The nominal duration to add.
     * @return A new date with the nominal duration added.
     */
    public Iso8601Date subtractNominal(final Iso8601Duration theDuration) {
        final LocalDate currentDate = LocalDate.of(year, month == 0 ? 1 : month, day == 0 ? 1 : day);
        final LocalDate newDate = currentDate.minusYears(theDuration.years())
                .minusMonths(theDuration.months())
                .minusWeeks(theDuration.weeks())
                .minusDays(theDuration.days());

        String value = String.format("%04d-%02d-%02d", newDate.getYear(), newDate.getMonthValue(), newDate.getDayOfMonth());
        return new Iso8601Date(value, timezone, newDate.getYear(), monthUnknown() ? 0 : newDate.getMonthValue(),
                dayUnknown() ? 0 : newDate.getDayOfMonth());
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
    protected int compareTo(@Nonnull Temporal theObject) {
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

    @Override
    public int compareTo(@Nonnull Iso8601Date theOther) {
        return compareTo((Temporal) theOther);
    }

    @JsonProperty("_type")
    private String getType() {
        return "DATE";
    }

}
