package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Iso8601TimezoneTests {

    @Test
    void validTimezoneWithZ() {
        Iso8601Timezone timezone = Iso8601Timezone.of("Z");
        assertEquals(0, timezone.hour());
        assertEquals(0, timezone.minute());
        assertEquals(1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
        assertTrue(timezone.isGmt());
    }

    @Test
    void validTimezoneWithPositiveOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+0230");
        assertEquals(2, timezone.hour());
        assertEquals(30, timezone.minute());
        assertEquals(1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
    }

    @Test
    void validTimezoneWithNegativeOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("-0500");
        assertEquals(5, timezone.hour());
        assertEquals(0, timezone.minute());
        assertEquals(-1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
    }

    @Test
    void validTimezoneWithExtendedFormat() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+02:30");
        assertEquals(2, timezone.hour());
        assertEquals(30, timezone.minute());
        assertEquals(1, timezone.sign());
        assertTrue(timezone.isExtended());
    }

    @Test
    void invalidTimezoneThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> Iso8601Timezone.of("invalid"));
    }

    @Test
    void compareToSameTimezone() {
        Iso8601Timezone timezone1 = Iso8601Timezone.of("+0200");
        Iso8601Timezone timezone2 = Iso8601Timezone.of("+0200");
        assertEquals(0, timezone1.compareTo(timezone2));
    }

    @Test
    void compareToDifferentSign() {
        Iso8601Timezone timezone1 = Iso8601Timezone.of("+0200");
        Iso8601Timezone timezone2 = Iso8601Timezone.of("-0200");
        assertTrue(timezone1.compareTo(timezone2) > 0);
    }

    @Test
    void compareToDifferentHour() {
        Iso8601Timezone timezone1 = Iso8601Timezone.of("+0200");
        Iso8601Timezone timezone2 = Iso8601Timezone.of("+0300");
        assertTrue(timezone1.compareTo(timezone2) < 0);
    }

    @Test
    void compareToDifferentMinute() {
        Iso8601Timezone timezone1 = Iso8601Timezone.of("+0200");
        Iso8601Timezone timezone2 = Iso8601Timezone.of("+0230");
        assertTrue(timezone1.compareTo(timezone2) < 0);
    }

    @Test
    void compareToNonIso8601TimezoneThrowsException() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+0200");
        assertThrows(IllegalArgumentException.class, () -> timezone.CompareTo(new Temporal() {

            @Override
            @SuppressWarnings("NullableProblems")
            public int CompareTo(Temporal o) {
                return 0;
            }

        }));
    }

    @Test
    void asStringWithPositiveOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+0230");
        assertEquals("+02:30", timezone.asString());
    }

    @Test
    void asStringWithNegativeOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("-0500");
        assertEquals("-05:00", timezone.asString());
    }

    @Test
    void asStringWithZ() {
        Iso8601Timezone timezone = Iso8601Timezone.of("Z");
        assertEquals("+00:00", timezone.asString());
    }

    @Test
    void isGmtWithZ() {
        Iso8601Timezone timezone = Iso8601Timezone.of("Z");
        assertTrue(timezone.isUtc());
    }

    @Test
    void isGmtWithPositiveOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+0230");
        assertFalse(timezone.isUtc());
    }

    @Test
    void isGmtWithNegativeOffset() {
        Iso8601Timezone timezone = Iso8601Timezone.of("-0500");
        assertFalse(timezone.isUtc());
    }

    @Test
    void isGmtWithNegative0000() {
        Iso8601Timezone timezone = Iso8601Timezone.of("-0000");
        assertTrue(timezone.isUtc());
    }

    @Test
    void isGmtWithPositive0000() {
        Iso8601Timezone timezone = Iso8601Timezone.of("+00:00");
        assertTrue(timezone.isUtc());
    }

}
