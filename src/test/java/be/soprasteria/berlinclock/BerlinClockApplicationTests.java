package be.soprasteria.berlinclock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BerlinClockApplicationTests {

    @Test
    void testEvenSecondsShouldTurnOnLamp() {
        BerlinClock clock = new BerlinClock();
        assertEquals("Y", clock.getSecondsLamp(0));
        assertEquals("Y", clock.getSecondsLamp(2));
        assertEquals("Y", clock.getSecondsLamp(58));
    }

    @Test
    void testOddSecondsShouldTurnOffLamp() {
        BerlinClock clock = new BerlinClock();
        assertEquals("O", clock.getSecondsLamp(1));
        assertEquals("O", clock.getSecondsLamp(3));
        assertEquals("O", clock.getSecondsLamp(59));
    }

    @Test
    void testFiveHourBlocks() {
        BerlinClock clock = new BerlinClock();
        assertEquals("OOOO", clock.getFiveHourRow(0));  // 0 hours, no lights
        assertEquals("ROOO", clock.getFiveHourRow(5));  // 5 hours, 1 light
        assertEquals("RROO", clock.getFiveHourRow(10)); // 10 hours, 2 lights
        assertEquals("RRRR", clock.getFiveHourRow(20)); // 20 hours, 4 lights
    }

    @Test
    void testSingleHourBlocks() {
        BerlinClock clock = new BerlinClock();
        assertEquals("OOOO", clock.getSingleHourRow(0));  // 0 additional hours
        assertEquals("ROOO", clock.getSingleHourRow(1));  // 1 additional hour
        assertEquals("RRRR", clock.getSingleHourRow(4));  // 4 additional hours
    }

    @Test
    void testFiveMinuteBlocks() {
        BerlinClock clock = new BerlinClock();
        assertEquals("OOOOOOOOOOO", clock.getFiveMinuteRow(0));   // 0 minutes
        assertEquals("YYROOOOOOOO", clock.getFiveMinuteRow(15));  // 15 minutes, 3rd block is red
        assertEquals("YYRYOOOOOOO", clock.getFiveMinuteRow(20));  // 20 minutes
        assertEquals("YYRYYOOOOOO", clock.getFiveMinuteRow(25));  // 25 minutes
        assertEquals("YYRYYROOOOO", clock.getFiveMinuteRow(30));  // 30 minutes, 6th block is red
        assertEquals("YYRYYRYOOOO", clock.getFiveMinuteRow(36));  // 36 minutes
        assertEquals("YYRYYRYYOOO", clock.getFiveMinuteRow(40));  // 35 minutes
    }

    @Test
    void testSingleMinuteBlocks() {
        BerlinClock clock = new BerlinClock();
        assertEquals("OOOO", clock.getSingleMinuteRow(0));  // 0 additional minutes
        assertEquals("YOOO", clock.getSingleMinuteRow(1));  // 1 additional minute
        assertEquals("YYYY", clock.getSingleMinuteRow(4));  // 4 additional minutes
    }
}
