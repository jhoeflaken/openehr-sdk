package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Iso8601TimeValidationTests {

    private final TimeDefinitions timeDefinitions = new TimeDefinitions() {};

    @Test
    void shouldApproveExtendedHHMMSS() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59"));
    }

    @Test
    void shouldApproveExtendedHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("23:59"));
    }

    @Test
    void shouldApproveExtendedHH() {
        assertTrue(timeDefinitions.validIso8601Time("23"));
    }

    @Test
    void shouldApproveExtendedHHMMSSFFF() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59.999"));
    }

    @Test
    void shouldApproveExtendedHHMMSSFFFComma() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59,999"));
    }

    @Test
    void shouldApproveExtendedHHMMSSZ() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59Z"));
    }

    @Test
    void shouldApproveExtendedHHMMSSPlusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59+02:00"));
    }

    @Test
    void shouldApproveExtendedHHMMSSMinusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59-02:00"));
    }


    @Test
    void shouldApproveCompactHHMMSS() {
        assertTrue(timeDefinitions.validIso8601Time("235959"));
    }

    @Test
    void shouldApproveCompactHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("2359"));
    }

    @Test
    void shouldApproveCompactHHMMSSFFF() {
        assertTrue(timeDefinitions.validIso8601Time("235959.999"));
    }

    @Test
    void shouldApproveCompactHHMMSSFFFComma() {
        assertTrue(timeDefinitions.validIso8601Time("235959,999"));
    }

    @Test
    void shouldApproveCompactHHMMSSZ() {
        assertTrue(timeDefinitions.validIso8601Time("235959Z"));
    }

    @Test
    void shouldApproveCompactHHMMSSPlusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("235959+02:00"));
    }

    @Test
    void shouldApproveCompactHHMMSSMinusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("235959-02:00"));
    }

    @Test
    void shouldApproveCompactHHPlusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("23+02:00"));
    }

    @Test
    void shouldApproveExtendedHHMMPlusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("23:00+02:00"));
    }

    @Test
    void shouldApproveCompactHHMMPlusHHMM() {
        assertTrue(timeDefinitions.validIso8601Time("2300+02:00"));
    }

    @Test
    void shouldApproveCompactFull() {
        assertTrue(timeDefinitions.validIso8601Time("235959.999+02:00"));
    }

    @Test
    void shouldApproveExtendedFull() {
        assertTrue(timeDefinitions.validIso8601Time("23:59:59.999+02:00"));
    }


    @Test
    void shouldNotApprove() {
        // Extended
        assertFalse(timeDefinitions.validIso8601Time("7")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("23:6")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("24:00:00")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("23:60:00")); // Invalid minute
        assertFalse(timeDefinitions.validIso8601Time("23:59:60")); // Invalid second
        assertFalse(timeDefinitions.validIso8601Time("23:59:59.9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("23:59:59,9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("23:59:59+25:00")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("23:59:59-25:00")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("23:59:59+02:60")); // Invalid timezone minute
        assertFalse(timeDefinitions.validIso8601Time("23:59:59-02:60")); // Invalid timezone minute

        // Compact
        assertFalse(timeDefinitions.validIso8601Time("7")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("236")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("240000")); // Invalid hour
        assertFalse(timeDefinitions.validIso8601Time("236000")); // Invalid minute
        assertFalse(timeDefinitions.validIso8601Time("235960")); // Invalid second
        assertFalse(timeDefinitions.validIso8601Time("235959.9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("235959,9999999")); // Too many fractional digits
        assertFalse(timeDefinitions.validIso8601Time("235959+2500")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("235959-2500")); // Invalid timezone hour
        assertFalse(timeDefinitions.validIso8601Time("235959+0260")); // Invalid timezone minute
        assertFalse(timeDefinitions.validIso8601Time("235959-0260")); // Invalid timezone minute
    }


}
