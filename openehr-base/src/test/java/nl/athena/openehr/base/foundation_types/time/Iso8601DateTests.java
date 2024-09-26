package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Iso8601DateTests {

    @Test
    void shouldAddDuration() {
        Iso8601Date date = new Iso8601Date("2021-12-31");
        Iso8601Duration duration = new Iso8601Duration("P1Y2M3W4D");
        Iso8601Date newDate = date.add(duration);
        assertEquals("2023-03-28", newDate.asString());
    }

    @Test
    void shouldSubtractDuration() {
        Iso8601Date date = new Iso8601Date("20211231");
        Iso8601Duration duration = new Iso8601Duration("P1Y2M3W4D");
        Iso8601Date newDate = date.subtract(duration);
        assertEquals("2020-10-03", newDate.asString());
    }

    @Test
    void shouldCalculateDiff() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        Iso8601Date date2 = new Iso8601Date("2020-10-03");
        Iso8601Duration duration = date1.diff(date2);
        assertEquals("P1Y2M28D", duration.toString());
    }

    @Test
    void shouldAddNominalDuration() {
        Iso8601Date date = new Iso8601Date("20210131");
        Iso8601Duration duration = new Iso8601Duration("P1M");
        Iso8601Date newDate = date.addNominal(duration);
        assertEquals("2021-02-28", newDate.asString());
    }

    @Test
    void shouldSubtractNominalDuration() {
        Iso8601Date date = new Iso8601Date("2021-03-31");
        Iso8601Duration duration = new Iso8601Duration("P1M");
        Iso8601Date newDate = date.subtractNominal(duration);
        assertEquals("2021-02-28", newDate.asString());
    }

    @Test
    void shouldReturnAsStringYYYYMMDD() {
        Iso8601Date date = new Iso8601Date("20211231");
        assertEquals("2021-12-31", date.asString());
    }

    @Test
    void shouldReturnAsStringYYYYMM() {
        Iso8601Date date = new Iso8601Date("202112");
        assertEquals("2021-12", date.asString());
    }

    @Test
    void shouldReturnAsStringYYYY() {
        Iso8601Date date = new Iso8601Date("2021");
        assertEquals("2021", date.asString());
    }

    @Test
    void shouldAddNominalDurationToYYYYMM() {
        Iso8601Date date = new Iso8601Date("2021-12");
        Iso8601Duration duration = new Iso8601Duration("P1Y12D");
        Iso8601Date newDate = date.addNominal(duration);
        assertEquals("2022-12", newDate.asString());
    }

    @Test
    void shouldSubtractNominalDurationFromYYYYMM() {
        Iso8601Date date = new Iso8601Date("2021-12");
        Iso8601Duration duration = new Iso8601Duration("P1Y12D");
        Iso8601Date newDate = date.subtractNominal(duration);
        assertEquals("2020-12", newDate.asString());
    }

    @Test
    void shouldCalculateDiffForYYYYMM() {
        Iso8601Date date1 = new Iso8601Date("2021-12");
        Iso8601Date date2 = new Iso8601Date("2020-10");
        Iso8601Duration duration = date1.diff(date2);
        assertEquals("P1Y2M", duration.toString());
    }

    @Test
    void shouldAddNominalDurationToYYYY() {
        Iso8601Date date = new Iso8601Date("2021");
        Iso8601Duration duration = new Iso8601Duration("P1Y12D");
        Iso8601Date newDate = date.addNominal(duration);
        assertEquals("2022", newDate.asString());
    }

    @Test
    void shouldSubtractNominalDurationFromYYYY() {
        Iso8601Date date = new Iso8601Date("2021");
        Iso8601Duration duration = new Iso8601Duration("P1Y12D");
        Iso8601Date newDate = date.subtractNominal(duration);
        assertEquals("2020", newDate.asString());
    }

    @Test
    void shouldCalculateDiffForYYYY() {
        Iso8601Date date1 = new Iso8601Date("2021");
        Iso8601Date date2 = new Iso8601Date("2020");
        Iso8601Duration duration = date1.diff(date2);
        assertEquals("P1Y", duration.toString());
    }

    @Test
    void shouldThrowExceptionForInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Date("2021-13-01"));
    }

    @Test
    void shouldThrowExceptionForInvalidDayInMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Date("2021-02-30"));
    }

    @Test
    void shouldThrowExceptionForInvalidMonth() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Date("2021-00-01"));
    }

    @Test
    void shouldThrowExceptionForInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Date("900-01-01"));
    }

    @Test
    void shouldThrowExceptionForInvalidDateLength() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Date("2021-1-1"));
    }

    @Test
    void shouldReturnTrueForMonthUnknown() {
        Iso8601Date date = new Iso8601Date("2021");
        assertTrue(date.monthUnknown());
    }

    @Test
    void shouldReturnFalseForMonthKnown() {
        Iso8601Date date = new Iso8601Date("2021-12");
        assertFalse(date.monthUnknown());
    }

    @Test
    void shouldReturnTrueForDayUnknown() {
        Iso8601Date date = new Iso8601Date("2021-12");
        assertTrue(date.dayUnknown());
    }

    @Test
    void shouldReturnFalseForDayKnown() {
        Iso8601Date date = new Iso8601Date("2021-12-31");
        assertFalse(date.dayUnknown());
    }

    @Test
    void shouldCompareDatesCorrectly() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        Iso8601Date date2 = new Iso8601Date("2021-12-30");
        assertTrue(date1.compareTo(date2) > 0);

        Iso8601Date date3 = new Iso8601Date("2021-12-31");
        assertEquals(0, date1.compareTo(date3));

        Iso8601Date date4 = new Iso8601Date("2022-01-01");
        assertTrue(date1.compareTo(date4) < 0);
    }

    @Test
    void shouldReturnTrueForEqualDates() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        Iso8601Date date2 = new Iso8601Date("2021-12-31");
        assertEquals(date1, date2);
    }

    @Test
    void shouldReturnFalseForDifferentDates() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        Iso8601Date date2 = new Iso8601Date("2021-12-30");
        assertNotEquals(date1, date2);
    }

    @Test
    void shouldReturnFalseForDifferentFormats() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        Iso8601Date date2 = new Iso8601Date("20211231");
        assertNotEquals(date1, date2);
    }

    @Test
    void shouldReturnFalseForNull() {
        Iso8601Date date1 = new Iso8601Date("2021-12-31");
        assertNotEquals(date1, null);
    }

}
