package be.soprasteria.berlinclock;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BerlinClockServiceTests {

    @Test
    void testEvenSecondsShouldTurnOnLamp() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("Y", clock.getSecondsLamp(0));
        assertEquals("Y", clock.getSecondsLamp(2));
        assertEquals("Y", clock.getSecondsLamp(58));
    }

    @Test
    void testOddSecondsShouldTurnOffLamp() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("O", clock.getSecondsLamp(1));
        assertEquals("O", clock.getSecondsLamp(3));
        assertEquals("O", clock.getSecondsLamp(59));
    }

    @Test
    void testFiveHourBlocks() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("OOOO", clock.getFiveHourLamp(0));  // 0 hours, no lights
        assertEquals("ROOO", clock.getFiveHourLamp(5));  // 5 hours, 1 light
        assertEquals("RROO", clock.getFiveHourLamp(10)); // 10 hours, 2 lights
        assertEquals("RRRR", clock.getFiveHourLamp(20)); // 20 hours, 4 lights
    }

    @Test
    void testSingleHourBlocks() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("OOOO", clock.getSingleHourLamp(0));  // 0 additional hours
        assertEquals("ROOO", clock.getSingleHourLamp(1));  // 1 additional hour
        assertEquals("RRRR", clock.getSingleHourLamp(4));  // 4 additional hours
    }

    @Test
    void testFiveMinuteBlocks() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("OOOOOOOOOOO", clock.getFiveMinuteLamp(0));   // 0 minutes
        assertEquals("YYROOOOOOOO", clock.getFiveMinuteLamp(15));  // 15 minutes, 3rd block is red
        assertEquals("YYRYOOOOOOO", clock.getFiveMinuteLamp(20));  // 20 minutes
        assertEquals("YYRYYOOOOOO", clock.getFiveMinuteLamp(25));  // 25 minutes
        assertEquals("YYRYYROOOOO", clock.getFiveMinuteLamp(30));  // 30 minutes, 6th block is red
        assertEquals("YYRYYRYOOOO", clock.getFiveMinuteLamp(36));  // 36 minutes
        assertEquals("YYRYYRYYOOO", clock.getFiveMinuteLamp(40));  // 35 minutes
    }

    @Test
    void testSingleMinuteBlocks() {
        BerlinClockService clock = new BerlinClockService();
        assertEquals("OOOO", clock.getSingleMinuteLamp(0));  // 0 additional minutes
        assertEquals("YOOO", clock.getSingleMinuteLamp(1));  // 1 additional minute
        assertEquals("YYYY", clock.getSingleMinuteLamp(4));  // 4 additional minutes
    }

    @Test
    void testBerlinClockAt183500() {
        BerlinClockService clock = new BerlinClockService();
        LocalTime time = LocalTime.of(18, 35, 0);
        var expected = """
                Y
                RRRO
                RRRO
                YYRYYRYOOOO
                OOOO""";

        assertEquals(expected, clock.displayBerlinClock(time));
    }

    @Test
    void testBerlinClockAtMidnight() {
        BerlinClockService clock = new BerlinClockService();
        LocalTime time = LocalTime.of(0, 0, 0);
        var expected = """
                Y
                OOOO
                OOOO
                OOOOOOOOOOO
                OOOO""";

        assertEquals(expected, clock.displayBerlinClock(time));
    }

    @Test
    void testBerlinClockAtMaxTime() {
        BerlinClockService clock = new BerlinClockService();
        LocalTime time = LocalTime.of(23, 59, 59);
        String expected = """
                O
                RRRR
                RRRO
                YYRYYRYYRYY
                YYYY""";

        assertEquals(expected, clock.displayBerlinClock(time));
    }
}
