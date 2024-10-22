package be.soprasteria.berlinclock;

import be.soprasteria.berlinclock.model.BerlinClockResponse;
import be.soprasteria.berlinclock.service.BerlinClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BerlinClockServiceTests {

    BerlinClockService sut;

    @BeforeEach
    void setUp() {
        sut = new BerlinClockService();
    }


    @Test
    void testEvenSecondsShouldTurnOnLamp() {
        assertEquals("Y", sut.getSecondsLamp(0));
        assertEquals("Y", sut.getSecondsLamp(2));
        assertEquals("Y", sut.getSecondsLamp(58));
    }

    @Test
    void testOddSecondsShouldTurnOffLamp() {
        assertEquals("O", sut.getSecondsLamp(1));
        assertEquals("O", sut.getSecondsLamp(3));
        assertEquals("O", sut.getSecondsLamp(59));
    }

    @Test
    void testFiveHourBlocks() {
        assertEquals("OOOO", sut.getFiveHourLamp(0));  // 0 hours, no lights
        assertEquals("ROOO", sut.getFiveHourLamp(5));  // 5 hours, 1 light
        assertEquals("RROO", sut.getFiveHourLamp(10)); // 10 hours, 2 lights
        assertEquals("RRRR", sut.getFiveHourLamp(20)); // 20 hours, 4 lights
    }

    @Test
    void testSingleHourBlocks() {
        assertEquals("OOOO", sut.getSingleHourLamp(0));  // 0 additional hours
        assertEquals("ROOO", sut.getSingleHourLamp(1));  // 1 additional hour
        assertEquals("RRRR", sut.getSingleHourLamp(4));  // 4 additional hours
    }

    @Test
    void testFiveMinuteBlocks() {
        assertEquals("OOOOOOOOOOO", sut.getFiveMinuteLamp(0));   // 0 minutes
        assertEquals("YYROOOOOOOO", sut.getFiveMinuteLamp(15));  // 15 minutes, 3rd block is red
        assertEquals("YYRYOOOOOOO", sut.getFiveMinuteLamp(20));  // 20 minutes
        assertEquals("YYRYYOOOOOO", sut.getFiveMinuteLamp(25));  // 25 minutes
        assertEquals("YYRYYROOOOO", sut.getFiveMinuteLamp(30));  // 30 minutes, 6th block is red
        assertEquals("YYRYYRYOOOO", sut.getFiveMinuteLamp(36));  // 36 minutes
        assertEquals("YYRYYRYYOOO", sut.getFiveMinuteLamp(40));  // 35 minutes
    }

    @Test
    void testSingleMinuteBlocks() {
        assertEquals("OOOO", sut.getSingleMinuteLamp(0));  // 0 additional minutes
        assertEquals("YOOO", sut.getSingleMinuteLamp(1));  // 1 additional minute
        assertEquals("YYYY", sut.getSingleMinuteLamp(4));  // 4 additional minutes
    }

    @Test
    void testBerlinClockAt183500() {
        LocalTime time = LocalTime.of(18, 35, 0);

        var expected = new BerlinClockResponse(
                "Y",
                "OOOO",
                "YYRYYRYOOOO",
                "RRRO",
                "RRRO");

        assertEquals(expected, sut.displayBerlinClock(time));
    }

    @Test
    void testBerlinClockAtMidnight() {
        LocalTime time = LocalTime.of(0, 0, 0);
        var expected = new BerlinClockResponse(
                "Y",
                "OOOO",
                "OOOOOOOOOOO",
                "OOOO",
                "OOOO");
        assertEquals(expected, sut.displayBerlinClock(time));
    }

    @Test
    void testBerlinClockAtMaxTime() {
        LocalTime time = LocalTime.of(23, 59, 59);
        var expected = new BerlinClockResponse(
                "O",
                "YYYY",
                "YYRYYRYYRYY",
                "RRRO",
                "RRRR");

        assertEquals(expected, sut.displayBerlinClock(time));
    }
}
