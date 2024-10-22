package be.soprasteria.berlinclock.model;

public record BerlinClockResponse(
        String secondsLamp,
        String minutesLamp,
        String fiveMinutesLamp,
        String hoursLamp,
        String fiveHoursLamp
) {
}
