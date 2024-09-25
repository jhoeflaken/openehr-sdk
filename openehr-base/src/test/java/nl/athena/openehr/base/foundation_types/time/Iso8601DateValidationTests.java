package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link TimeDefinitions#validIso8601Date}.
 */
public class Iso8601DateValidationTests {

    private final TimeDefinitions timeDefinitions = new TimeDefinitions() {};

    @Test
    void shouldApproveExtendedYYYYMMDD() {
        String date = "2021-12-31";
        boolean valid = timeDefinitions.validIso8601Date(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedYYYYMM() {
        String date = "2021-12";
        boolean valid = timeDefinitions.validIso8601Date(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedYYYY() {
        String date = "2021";
        boolean valid = timeDefinitions.validIso8601Date(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactYYYYMMDD() {
        String date = "20211231";
        boolean valid = timeDefinitions.validIso8601Date(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactYYYYMM() {
        String date = "202112";
        boolean valid = timeDefinitions.validIso8601Date(date);
        assertTrue(valid);
    }


    @Test
    void shouldNotApprove() {
        // Extended
        assertFalse(timeDefinitions.validIso8601Date("2023-02-29")); // Not a leap year
        assertFalse(timeDefinitions.validIso8601Date("2023-13-01")); // Invalid month
        assertFalse(timeDefinitions.validIso8601Date("2023-00-10")); // Invalid month
        assertFalse(timeDefinitions.validIso8601Date("2023-10-32")); // Invalid day
        assertFalse(timeDefinitions.validIso8601Date("2023-10-5"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601Date("2023-1-05"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601Date("20"));         // Invalid format
        assertFalse(timeDefinitions.validIso8601Date("2023-10-05T12:00:00")); // DateTime format

        // Compact
        assertFalse(timeDefinitions.validIso8601Date("20230229")); // Not a leap year
        assertFalse(timeDefinitions.validIso8601Date("20231301")); // Invalid month
        assertFalse(timeDefinitions.validIso8601Date("20230010")); // Invalid month
        assertFalse(timeDefinitions.validIso8601Date("20231032")); // Invalid day
        assertFalse(timeDefinitions.validIso8601Date("2023105"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601Date("202315"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601Date("20231005T120000")); // DateTime format
    }

}
