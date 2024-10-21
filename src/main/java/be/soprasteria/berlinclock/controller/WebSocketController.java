package be.soprasteria.berlinclock.controller;

import be.soprasteria.berlinclock.model.BerlinClockResponse;
import be.soprasteria.berlinclock.service.BerlinClockService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class WebSocketController {

    private final BerlinClockService berlinClockService;
    private final SimpMessagingTemplate messagingTemplate;


    public WebSocketController(BerlinClockService berlinClockService, SimpMessagingTemplate messagingTemplate) {
        this.berlinClockService = berlinClockService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000) // every second
    public void sendBerlinClockUpdate() {
        LocalTime currentTime = LocalTime.now();
        int hours = currentTime.getHour();
        int minutes = currentTime.getMinute();
        int seconds = currentTime.getSecond();

        var response = new BerlinClockResponse(
                berlinClockService.getSecondsLamp(seconds),
                berlinClockService.getSingleMinuteLamp(minutes),
                berlinClockService.getFiveMinuteLamp(minutes),
                berlinClockService.getSingleHourLamp(hours),
                berlinClockService.getFiveHourLamp(hours)
        );
        messagingTemplate.convertAndSend("/topic/clock", response);
    }
}
