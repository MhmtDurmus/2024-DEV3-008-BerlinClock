package be.soprasteria.berlinclock.controller;

import be.soprasteria.berlinclock.model.BerlinClockResponse;
import be.soprasteria.berlinclock.service.BerlinClockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class WebSocketControllerTest {

    @Mock
    private BerlinClockService berlinClockService;

    @Mock
    private SimpMessagingTemplate messagingTemplate;

    @InjectMocks
    private WebSocketController webSocketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendBerlinClockUpdate() {
        // GIVEN
        LocalTime mockTime = LocalTime.of(10, 15, 30); // 10:15:30
        var expectedResult = new BerlinClockResponse("Y", "Y", "R", "Y", "R");
        // WHEN
        when(berlinClockService.displayBerlinClock(any())).thenReturn(expectedResult);

        // THEN
        webSocketController.sendBerlinClockUpdate(mockTime);

        ArgumentCaptor<BerlinClockResponse> responseCaptor = ArgumentCaptor.forClass(BerlinClockResponse.class);
        verify(messagingTemplate).convertAndSend(eq("/topic/clock"), responseCaptor.capture());

        // RESULT
        var response = responseCaptor.getValue();
        assertEquals("Y", response.secondsLamp());
        assertEquals("Y", response.minutesLamp());
        assertEquals("R", response.fiveMinutesLamp());
        assertEquals("Y", response.hoursLamp());
        assertEquals("R", response.fiveHoursLamp());
    }
}