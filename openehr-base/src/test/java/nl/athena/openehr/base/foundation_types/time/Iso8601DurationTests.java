package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Iso8601DurationTests {

    @Test
    void shouldCreateValidIso8601Duration() {
        Iso8601Duration duration = Iso8601Duration.of("P1Y2M3W4DT5H6M7.9999S");
        assertNotNull(duration);
        assertEquals(1, duration.years());
        assertEquals(2, duration.months());
        assertEquals(3, duration.weeks());
        assertEquals(4, duration.days());
        assertEquals(5, duration.hours());
        assertEquals(6, duration.minutes());
        assertEquals(7, duration.seconds());
        assertEquals(0.9999f, duration.fractionalSeconds());
        assertFalse(duration.isDecimalSignComma());
    }

    @Test
    void shouldCreateValidNegativeIso8601Duration() {
        Iso8601Duration duration = Iso8601Duration.of("-P1Y2M3W4DT5H6M7,999S");
        assertNotNull(duration);
        assertEquals(-1, duration.years());
        assertEquals(-2, duration.months());
        assertEquals(-3, duration.weeks());
        assertEquals(-4, duration.days());
        assertEquals(-5, duration.hours());
        assertEquals(-6, duration.minutes());
        assertEquals(-7, duration.seconds());
        assertEquals(-0.999f, duration.fractionalSeconds());
        assertTrue(duration.isDecimalSignComma());
    }

    @Test
    void shouldThrowExceptionForInvalidIso8601Duration() {
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P-1Y"));
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P1Y-2M"));
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P1Y2M3W4DT5H6M7.1234S67"));
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P1Y2M3W4DT5H6M7,1234S67"));
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P1Y2M3W4DT5H6M7.123S8M"));
        assertThrows(IllegalArgumentException.class, () -> Iso8601Duration.of("P5H6M7S"));
    }

    @Test
    void shouldAddDurations() {
        Iso8601Duration duration1 = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        Iso8601Duration duration2 = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        Iso8601Duration result = duration1.add(duration2);
        assertEquals(duration1.toSeconds() + duration2.toSeconds(), result.toSeconds());
    }

    @Test
    void shouldSubtractDurations() {
        Iso8601Duration duration1 = Iso8601Duration.of("P2Y4M6W8DT10H12M14S");
        Iso8601Duration duration2 = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        Iso8601Duration result = duration1.subtract(duration2);
        assertEquals(duration1.toSeconds() - duration2.toSeconds(), result.toSeconds());
    }

    @Test
    void shouldMultiplyDuration() {
        Iso8601Duration duration = Iso8601Duration.of("P1Y2M3W4DT5H6M9S");
        Iso8601Duration result = duration.multiply(2);
        assertEquals(duration.toSeconds() * 2, result.toSeconds());
    }

    @Test
    void shouldDivideDuration() {
        Iso8601Duration duration = Iso8601Duration.of("P2Y4M6W8DT10H12M9S");
        Iso8601Duration result = duration.divide(2);
        assertEquals(duration.toSeconds() / 2, result.toSeconds());
    }

    @Test
    void shouldNegateDuration() {
        Iso8601Duration duration = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        Iso8601Duration result = duration.negative();
        assertEquals(result.toSeconds(), result.toSeconds());
    }

    @Test
    void shouldCompareDurations() {
        Iso8601Duration duration1 = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        Iso8601Duration duration2 = Iso8601Duration.of("P1Y2M3W4DT5H6M7S");
        assertEquals(0, duration1.compareTo(duration2));
    }

    @Test
    void shouldConvertToSeconds() {
        Iso8601Duration duration = Iso8601Duration.of("P1Y2M3W4DT5H6M7.9999S");
        assertEquals(3.889837E7f, duration.toSeconds());
    }

    @Test
    void shouldConvertToString() {
        Iso8601Duration duration = Iso8601Duration.of("P1Y2M3W4DT5H6M7.9999S");
        assertEquals("P1Y2M3W4DT5H6M7.9999S", duration.asString());
    }

}
