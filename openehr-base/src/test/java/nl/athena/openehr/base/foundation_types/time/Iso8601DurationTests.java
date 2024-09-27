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

}
