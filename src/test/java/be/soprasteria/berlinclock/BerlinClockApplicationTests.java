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

}
