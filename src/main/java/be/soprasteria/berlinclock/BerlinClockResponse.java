package be.soprasteria.berlinclock;

public record BerlinClockResponse(
        String secondsLamp,
        String minutesLamp,
        String fiveMinutesLamp,
        String hoursLamp,
        String fiveHoursLamp
) {
}
