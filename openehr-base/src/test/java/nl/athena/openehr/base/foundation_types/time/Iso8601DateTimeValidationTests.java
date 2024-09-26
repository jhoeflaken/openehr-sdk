package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for the {@link TimeDefinitions#validIso8601DateTime}.
 */
public class Iso8601DateTimeValidationTests {

    private final TimeDefinitions timeDefinitions = new TimeDefinitions() {};

    @Test
    void shouldApproveExtendedYYYYMMDDHHMMSS() {
        String date = "2021-12-31T23:59:59";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedYYYYMMDDHHSS() {
        String date = "2021-12-31T23:59";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedYYYYMMDDHH() {
        String date = "2021-12-31T23";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedFractionalPoint() {
        String date = "2021-12-31T23:59:59.999";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedFractionalComma() {
        String date = "2021-12-31T23:59:59,999";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }


    @Test
    void shouldApproveExtendedTimezonePlusHHMM() {
        String date = "2021-12-31T23:59:59+02:00";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedTimezoneMinusHH() {
        String date = "2021-12-31T23:59:59-02";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedTimezoneZulu() {
        String date = "2021-12-31T23:59:59Z";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveExtendedFull() {
        String date = "2021-12-31T23:59:59.999+02:00";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactYYYYMMDDHHMMSS() {
        String date = "20211231T235959";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactYYYYMMDDHHSS() {
        String date = "20211231T23:59";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactYYYYMMDDHH() {
        String date = "20211231T23";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactFractionalPoint() {
        String date = "20211231T235959.999";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactFractionalComma() {
        String date = "20211231T235959,999";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }


    @Test
    void shouldApproveCompactTimezonePlusHHMM() {
        String date = "20211231T235959+0200";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactTimezoneMinusHH() {
        String date = "20211231T235959-02";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactTimezoneZulu() {
        String date = "20211231T235959Z";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldApproveCompactFull() {
        String date = "20211231T235959.999+0200";
        boolean valid = timeDefinitions.validIso8601DateTime(date);
        assertTrue(valid);
    }

    @Test
    void shouldNotApprove() {
        // Extended
        assertFalse(timeDefinitions.validIso8601DateTime("2023-02-29T23:59:58")); // Not a leap year
        assertFalse(timeDefinitions.validIso8601DateTime("2023-13-01T23:59:58")); // Invalid month
        assertFalse(timeDefinitions.validIso8601DateTime("2023-00-10T23:59:58")); // Invalid month
        assertFalse(timeDefinitions.validIso8601DateTime("2023-10-32T23:59:58")); // Invalid day
        assertFalse(timeDefinitions.validIso8601DateTime("2023-10-5T23:59:58"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("2023-1-05T23:59:58"));  // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("2023-02-01"));          // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("2023-10-05T12:00:00.9999999")); // Invalid format
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T7"));        // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:6"));     // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T24:00:00")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:60:00")); // Invalid minute
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:60")); // Invalid second
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59.9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59,9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59.9999999"));  // Missing seconds
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59+25:00")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59-25:00")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59+02:60")); // Invalid timezone minute
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T23:59:59-02:60")); // Invalid timezone minute

        // Compact
        assertFalse(timeDefinitions.validIso8601DateTime("20230229T235958"));     // Not a leap year
        assertFalse(timeDefinitions.validIso8601DateTime("20231301T235958"));     // Invalid month
        assertFalse(timeDefinitions.validIso8601DateTime("20230010T235958"));     // Invalid month
        assertFalse(timeDefinitions.validIso8601DateTime("20231032T235958"));     // Invalid day
        assertFalse(timeDefinitions.validIso8601DateTime("2023105T235958"));      // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("2023105T235958"));      // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("20230201"));            // Invalid format
        assertFalse(timeDefinitions.validIso8601DateTime("20231005T120000.9999999")); // Invalid format
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T7"));        // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T236"));      // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T240000"));   // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T236000"));   // Invalid minute
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235960"));   // Invalid second
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959.9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959,9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("20230220T2359.9999999"));  // Missing seconds
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959+2500")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959-2500")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959+0260")); // Invalid timezone minute
        assertFalse(timeDefinitions.validIso8601Time("2023-02-20T235959-0260")); // Invalid timezone minute
    }

}
