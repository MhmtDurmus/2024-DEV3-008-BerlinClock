package be.soprasteria.berlinclock;

public class BerlinClock {

    public String getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? "Y" : "O";
    }
}
