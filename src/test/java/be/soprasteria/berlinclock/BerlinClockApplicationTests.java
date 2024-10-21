package be.soprasteria.berlinclock;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BerlinClockApplicationTests {


    @Test
    void contextLoads() {
    }

    @Test
    public void testEvenSecondsShouldTurnOnLamp() {
        BerlinClock clock = new BerlinClock();
        assertEquals("Y", clock.getSecondsLamp(0));
        assertEquals("Y", clock.getSecondsLamp(2));
        assertEquals("Y", clock.getSecondsLamp(58));
    }

    @Test
    public void testOddSecondsShouldTurnOffLamp() {
        BerlinClock clock = new BerlinClock();
        assertEquals("O", clock.getSecondsLamp(1));
        assertEquals("O", clock.getSecondsLamp(3));
        assertEquals("O", clock.getSecondsLamp(59));
    }

}
