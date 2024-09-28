package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Iso8601TimeTests {

    @Test
    void ofStringWithoutFractionalSeconds() {
        Iso8601Time time = Iso8601Time.of("12:30:45");
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(45, time.seconds());
        assertFalse(time.minuteUnknown());
        assertFalse(time.secondUnknown());
        assertFalse(time.hasFractionalSeconds());
    }

    @Test
    void ofStringWithFractionalSeconds() {
        Iso8601Time time = Iso8601Time.of("12:30:45.123");
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(45, time.seconds());
        assertFalse(time.minuteUnknown());
        assertFalse(time.secondUnknown());
        assertTrue(time.hasFractionalSeconds());
        assertEquals(0.123f, time.fractionalSeconds(), 0.001);
    }

    @Test
    void ofStringWithTimezone() {
        Iso8601Time time = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        assertFalse(time.minuteUnknown());
        assertFalse(time.secondUnknown());
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(45, time.seconds());
        assertEquals("+02:00", time.timezone().asString());
    }

    @Test
    void ofStringWithFractionalSecondsAndTimezone() {
        Iso8601Time time = Iso8601Time.of("12:30:45.123", Iso8601Timezone.of("+0200"));
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(45, time.seconds());
        assertFalse(time.minuteUnknown());
        assertFalse(time.secondUnknown());
        assertTrue(time.hasFractionalSeconds());
        assertEquals(0.123f, time.fractionalSeconds(), 0.001);
        assertEquals("+02:00", time.timezone().asString());
    }

    @Test
    void ofStringWithHoursOnly() {
        Iso8601Time time = Iso8601Time.of("12");
        assertEquals(12, time.hours());
        assertEquals(0, time.minutes());
        assertEquals(0, time.seconds());
        assertFalse(time.hasFractionalSeconds());
        assertTrue(time.minuteUnknown());
        assertTrue(time.secondUnknown());
        assertTrue(time.isPartial());
    }

    @Test
    void ofStringWithHoursAndMinutesOnly() {
        Iso8601Time time = Iso8601Time.of("12:30");
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(0, time.seconds());
        assertFalse(time.hasFractionalSeconds());
        assertFalse(time.minuteUnknown());
        assertTrue(time.secondUnknown());
        assertTrue(time.isPartial());
    }

    @Test
    void ofStringWithCompactFormat() {
        Iso8601Time time = Iso8601Time.of("123045");
        assertEquals(12, time.hours());
        assertEquals(30, time.minutes());
        assertEquals(45, time.seconds());
        assertFalse(time.hasFractionalSeconds());
        assertFalse(time.minuteUnknown());
        assertFalse(time.secondUnknown());
    }

    @Test
    void asString() {
        Iso8601Time time = Iso8601Time.of("12:30:45.123", Iso8601Timezone.of("+0200"));
        assertEquals("12:30:45.123+02:00", time.asString());
    }

    @Test
    void asStringWithHoursOnly() {
        Iso8601Time time = Iso8601Time.of("12");
        assertEquals("12:00:00", time.asString());
    }

    @Test
    void asStringWithHoursAndMinutesOnly() {
        Iso8601Time time = Iso8601Time.of("12:30");
        assertEquals("12:30:00", time.asString());
    }

    @Test
    void addDurationToTime() {
        Iso8601Time time = Iso8601Time.of("12:30:45");
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30S");
        Iso8601Time newTime = time.add(duration);
        assertEquals("13:46:15", newTime.asString());
    }

    @Test
    void subtractDurationFromTime() {
        Iso8601Time time = Iso8601Time.of("12:30:45");
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30S");
        Iso8601Time newTime = time.subtract(duration);
        assertEquals("11:15:15", newTime.asString());
    }

    @Test
    void diffBetweenTimes() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45");
        Iso8601Time time2 = Iso8601Time.of("10:15:30");
        Iso8601Duration duration = time1.diff(time2);
        assertEquals(8115.0f, duration.toSeconds());
    }

    @Test
    void addDurationWithFractionalSeconds() {
        Iso8601Time time = Iso8601Time.of("12:30:45.123");
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30.456S");
        Iso8601Time newTime = time.add(duration);
        assertEquals("13:46:15.579", newTime.asString());
    }

    @Test
    void subtractDurationWithFractionalSeconds() {
        Iso8601Time time = Iso8601Time.of("12:30:45.123");
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30.456S");
        Iso8601Time newTime = time.subtract(duration);
        assertEquals("11:15:14.667", newTime.asString());
    }

    @Test
    void diffBetweenTimesWithFractionalSeconds() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45.123");
        Iso8601Time time2 = Iso8601Time.of("10:15:30.456");
        Iso8601Duration duration = time1.diff(time2);
        assertEquals(8114.667f, duration.toSeconds());
    }

    @Test
    void addDurationToTimeWithTimezone() {
        Iso8601Time time = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30S");
        Iso8601Time newTime = time.add(duration);
        assertEquals("13:46:15+02:00", newTime.asString());
    }

    @Test
    void subtractDurationFromTimeWithTimezone() {
        Iso8601Time time = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        Iso8601Duration duration = Iso8601Duration.of("PT1H15M30S");
        Iso8601Time newTime = time.subtract(duration);
        assertEquals("11:15:15+02:00", newTime.asString());
    }

    @Test
    void diffBetweenTimesWithTimezone() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        Iso8601Time time2 = Iso8601Time.of("10:15:30", Iso8601Timezone.of("+0200"));
        Iso8601Duration duration = time1.diff(time2);
        assertEquals(8115.0f, duration.toSeconds());
    }

    @Test
    void compareToEqualTimes() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45");
        Iso8601Time time2 = Iso8601Time.of("12:30:45");
        assertEquals(0, time1.compareTo(time2));
    }

    @Test
    void compareToEarlierTime() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45");
        Iso8601Time time2 = Iso8601Time.of("12:30:46");
        assertTrue(time1.compareTo(time2) < 0);
    }

    @Test
    void compareToLaterTime() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45");
        Iso8601Time time2 = Iso8601Time.of("12:30:44");
        assertTrue(time1.compareTo(time2) > 0);
    }

    @Test
    void compareToWithFractionalSeconds() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45.123");
        Iso8601Time time2 = Iso8601Time.of("12:30:45.124");
        assertTrue(time1.compareTo(time2) < 0);
    }

    @Test
    void compareToWithTimezone() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        Iso8601Time time2 = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0100"));
        assertTrue(time1.compareTo(time2) < 0);
    }

    @Test
    void diffBetweenTimesWithDifferentTimezones() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45", Iso8601Timezone.of("+0200"));
        Iso8601Time time2 = Iso8601Time.of("10:30:45", Iso8601Timezone.of("+0100"));
        Iso8601Duration duration = time1.diff(time2);
        assertEquals(3600.0f, duration.toSeconds());
    }

    @Test
    void diffBetweenTimesWithDifferentTimezonesAndFractionalSeconds() {
        Iso8601Time time1 = Iso8601Time.of("12:30:45.123", Iso8601Timezone.of("+0200"));
        Iso8601Time time2 = Iso8601Time.of("10:30:45.456", Iso8601Timezone.of("+0100"));
        Iso8601Duration duration = time1.diff(time2);
        assertEquals(3599.667f, duration.toSeconds());
    }

}
