package nl.athena.openehr.base.foundation_types.time;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Iso8601TimezoneTests {

    @Test
    void validTimezoneWithZ() {
        Iso8601Timezone timezone = new Iso8601Timezone("Z");
        assertEquals(0, timezone.hour());
        assertEquals(0, timezone.minute());
        assertEquals(1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
        assertTrue(timezone.isGmt());
    }

    @Test
    void validTimezoneWithPositiveOffset() {
        Iso8601Timezone timezone = new Iso8601Timezone("+0230");
        assertEquals(2, timezone.hour());
        assertEquals(30, timezone.minute());
        assertEquals(1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
    }

    @Test
    void validTimezoneWithNegativeOffset() {
        Iso8601Timezone timezone = new Iso8601Timezone("-0500");
        assertEquals(5, timezone.hour());
        assertEquals(0, timezone.minute());
        assertEquals(-1, timezone.sign());
        assertFalse(timezone.minuteUnknown());
    }

    @Test
    void validTimezoneWithExtendedFormat() {
        Iso8601Timezone timezone = new Iso8601Timezone("+02:30");
        assertEquals(2, timezone.hour());
        assertEquals(30, timezone.minute());
        assertEquals(1, timezone.sign());
        assertTrue(timezone.isExtended());
    }

    @Test
    void invalidTimezoneThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new Iso8601Timezone("invalid"));
    }

    @Test
    void compareToSameTimezone() {
        Iso8601Timezone timezone1 = new Iso8601Timezone("+0200");
        Iso8601Timezone timezone2 = new Iso8601Timezone("+0200");
        assertEquals(0, timezone1.compareTo(timezone2));
    }

    @Test
    void compareToDifferentSign() {
        Iso8601Timezone timezone1 = new Iso8601Timezone("+0200");
        Iso8601Timezone timezone2 = new Iso8601Timezone("-0200");
        assertTrue(timezone1.compareTo(timezone2) > 0);
    }

    @Test
    void compareToDifferentHour() {
        Iso8601Timezone timezone1 = new Iso8601Timezone("+0200");
        Iso8601Timezone timezone2 = new Iso8601Timezone("+0300");
        assertTrue(timezone1.compareTo(timezone2) < 0);
    }

    @Test
    void compareToDifferentMinute() {
        Iso8601Timezone timezone1 = new Iso8601Timezone("+0200");
        Iso8601Timezone timezone2 = new Iso8601Timezone("+0230");
        assertTrue(timezone1.compareTo(timezone2) < 0);
    }

    @Test
    void compareToNonIso8601TimezoneThrowsException() {
        Iso8601Timezone timezone = new Iso8601Timezone("+0200");
        assertThrows(IllegalArgumentException.class, () -> timezone.compareTo(new Temporal() {
            @Override
            public int compareTo(Temporal o) {
                return 0;
            }
        }));
    }

}
