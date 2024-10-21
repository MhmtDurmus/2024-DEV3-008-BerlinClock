package be.soprasteria.berlinclock;

import java.time.LocalTime;

public class BerlinClockService {

    public String displayBerlinClock(LocalTime time) {
        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        return getSecondsLamp(seconds) + "\n" +
                getFiveHourRow(hours) + "\n" +
                getSingleHourRow(hours) + "\n" +
                getFiveMinuteRow(minutes) + "\n" +
                getSingleMinuteRow(minutes);
    }

    public String getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? "Y" : "O";
    }

    public String getFiveHourRow(int hours) {
        int lampsOn = hours / 5;
        return getLamps(lampsOn, "R");
    }

    public String getSingleHourRow(int hours) {
        int lampsOn = hours % 5;
        return getLamps(lampsOn, "R");
    }

    public String getFiveMinuteRow(int minutes) {
        int lampsOn = minutes / 5;
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < 11; i++) {
            if (i < lampsOn) {
                row.append((i + 1) % 3 == 0 ? "R" : "Y");
            } else {
                row.append("O");
            }
        }
        return row.toString();
    }

    public String getSingleMinuteRow(int minutes) {
        int lampsOn = minutes % 5;
        return getLamps(lampsOn, "Y");
    }

    private String getLamps(int lampsOn, String onSymbol) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            row.append(i < lampsOn ? onSymbol : "O");
        }
        return row.toString();
    }
}
