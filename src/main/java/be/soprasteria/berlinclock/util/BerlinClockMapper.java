package be.soprasteria.berlinclock.util;

import be.soprasteria.berlinclock.model.BerlinClockResponse;
import com.example.berlinclock.model.BerlinClockPayloadApi;
import org.springframework.stereotype.Component;

@Component
public class BerlinClockMapper {

    public BerlinClockPayloadApi toApi(BerlinClockResponse berlinClockResponse) {
        return new BerlinClockPayloadApi()
                .seconds(berlinClockResponse.secondsLamp())
                .oneMinuteRow(berlinClockResponse.minutesLamp())
                .fiveMinuteRow(berlinClockResponse.fiveMinutesLamp())
                .oneHourRow(berlinClockResponse.hoursLamp())
                .fiveHourRow(berlinClockResponse.fiveHoursLamp());
    }
}
