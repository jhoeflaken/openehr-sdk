package nl.athena.openehr.base.foundation_types.time;

public class Iso8601Date extends Iso8601Type {

    private int month;
    private int day;

    /**
     * Extract the year part of the date as an Integer.
     *
     * @return Year part of the date as an Integer.
     */
    public int year() {
        return 0;
    }

    /**
     * Extract the month part of the date as an Integer, or return 0 if not present.
     *
     * @return Month part of the date as an Integer, or return 0 if not present.
     */
    public int month() {
        return 0;
    }

    /**
     * Extract the day part of the date as an Integer, or return 0 if not present.
     *
     * @return Day part of the date as an Integer, or return 0 if not present.
     */
    public int day() {
        return 0;
    }

    /**
     * Extract the time zone part of the date as an Iso8601TimeZone.
     *
     * @return Time zone part of the date as an Iso8601TimeZone. Can be null.
     */
    public Iso8601Timezone timeZone() {
        return null;
    }

    /**
     * True if the month part of the date is unknown.
     *
     * @return True if the month part of the date is unknown.
     */
    public boolean monthUnknown() {
        return false;
    }

    /**
     * True if the day part of the date is unknown.
     *
     * @return True if the day part of the date is unknown.
     */
    public boolean dayUnknown() {
        return false;
    }

    /**
     * The extended form of the date, with '-' separators.
     *
     * @return The extended form of the date, with '-' separators.
     */
    public String asString() {
        return null;
    }

    /**
     * Add a duration to this date, returning a new date.
     *
     * @param duration The duration to add.
     * @return A new date with the duration added.
     */
    public Iso8601Date add(final Iso8601Duration duration) {
        return null;
    }

    /**
     * Subtract a duration from this date, returning a new date.
     *
     * @param duration The duration to subtract.
     * @return A new date with the duration subtracted.
     */
    public Iso8601Date subtract(final Iso8601Duration duration) {
        return null;
    }

    /**
     * Calculate the difference between this date and another date.
     *
     * @param other The other date.
     * @return The difference between this date and the other date.
     */
    public Iso8601Duration diff(final Iso8601Date other) {
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
        return null;
    }

    /**
     * Subtract a nominal duration to this date, returning a new date. See {@link #addNominal} for
     * details and semantics.
     *
     * @param duration The nominal duration to add.
     * @return A new date with the nominal duration added.
     */
    public Iso8601Date subtractNominal(final Iso8601Duration duration) {
        return null;
    }

    @Override
    public boolean isPartial() {
        return false;
    }

    @Override
    public boolean isExtended() {
        return false;
    }


}
