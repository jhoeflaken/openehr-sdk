package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 ** Tests for the {@link TimeDefinitions#validIso8601Duration}.
 */
public class Iso8601DurationValidationTests {

    private final TimeDefinitions timeDefinitions = new TimeDefinitions() {};

    @Test
    void testValidIso8601Duration() {
        // Valid durations
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7S"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M3W4D"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y"));
        assertTrue(timeDefinitions.validIso8601Duration("P2M"));
        assertTrue(timeDefinitions.validIso8601Duration("P3W"));
        assertTrue(timeDefinitions.validIso8601Duration("P4D"));
        assertTrue(timeDefinitions.validIso8601Duration("PT5H"));
        assertTrue(timeDefinitions.validIso8601Duration("PT6M"));
        assertTrue(timeDefinitions.validIso8601Duration("PT7S"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M3W"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7.123S"));
        assertTrue(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7,123S"));

//        assertTrue(timeDefinitions.validIso8601Duration("P5H6M7S"));


        // Invalid durations
        assertFalse(timeDefinitions.validIso8601Duration("P"));
        assertFalse(timeDefinitions.validIso8601Duration("PT"));
        assertFalse(timeDefinitions.validIso8601Duration("P-1Y"));
        assertFalse(timeDefinitions.validIso8601Duration("P1Y-2M"));
        assertFalse(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7.1234S67")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7,1234S67")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Duration("P1Y2M3W4DT5H6M7.123S8M")); // Invalid format
    }

}
