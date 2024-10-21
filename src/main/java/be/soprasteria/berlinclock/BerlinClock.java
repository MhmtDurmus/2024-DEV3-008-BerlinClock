package be.soprasteria.berlinclock;

public class BerlinClock {

    public String getSecondsLamp(int seconds) {
        return (seconds % 2 == 0) ? "Y" : "O";
    }

    public String getFiveHourRow(int hours) {
        int lampsOn = hours / 5;
        return getLamps(lampsOn, 4, "R");
    }

    public String getSingleHourRow(int hours) {
        int lampsOn = hours % 5;
        return getLamps(lampsOn, 4, "R");
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
        return getLamps(lampsOn, 4, "Y");
    }

    private String getLamps(int lampsOn, int totalLamps, String onSymbol) {
        StringBuilder row = new StringBuilder();
        for (int i = 0; i < totalLamps; i++) {
            row.append(i < lampsOn ? onSymbol : "O");
        }
        return row.toString();
    }
}
