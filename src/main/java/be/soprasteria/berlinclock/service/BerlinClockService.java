package be.soprasteria.berlinclock.service;

import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class BerlinClockService {

    public String displayBerlinClock(LocalTime time) {
        int hours = time.getHour();
        int minutes = time.getMinute();
        int seconds = time.getSecond();

        return getSecondsLamp(seconds) + "\n" +
                getFiveHourLamp(hours) + "\n" +
                getSingleHourLamp(hours) + "\n" +
                getFiveMinuteLamp(minutes) + "\n" +
                getSingleMinuteLamp(minutes);
    }

    public String getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? "Y" : "O";
    }

    public String getFiveHourLamp(int hours) {
        int lampsOn = hours / 5;
        return getLamps(lampsOn, "R");
    }

    public String getSingleHourLamp(int hours) {
        int lampsOn = hours % 5;
        return getLamps(lampsOn, "R");
    }

    public String getFiveMinuteLamp(int minutes) {
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

    public String getSingleMinuteLamp(int minutes) {
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
