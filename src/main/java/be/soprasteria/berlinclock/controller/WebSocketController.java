package be.soprasteria.berlinclock.controller;

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

    public void sendBerlinClockUpdate(LocalTime currentTime) {
        var response = berlinClockService.displayBerlinClock(currentTime);

        messagingTemplate.convertAndSend("/topic/clock", response);
    }

    @Scheduled(fixedRate = 1000)
    public void scheduledSendBerlinClockUpdate() {
        sendBerlinClockUpdate(LocalTime.now());
    }

}
