package be.soprasteria.berlinclock.controller;

import be.soprasteria.berlinclock.service.BerlinClockService;
import be.soprasteria.berlinclock.util.BerlinClockMapper;
import com.example.berlinclock.api.BerlinClockApi;
import com.example.berlinclock.model.BerlinClockPayloadApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class BerlinClockController implements BerlinClockApi {
    private final BerlinClockMapper berlinClockMapper;
    private final BerlinClockService berlinClockService;

    public BerlinClockController(BerlinClockMapper berlinClockMapper, BerlinClockService berlinClockService) {
        this.berlinClockMapper = berlinClockMapper;
        this.berlinClockService = berlinClockService;
    }


    @Override
    public ResponseEntity<BerlinClockPayloadApi> getBerlinClock() {
        return ResponseEntity.ok(this.berlinClockMapper.toApi(
                berlinClockService.displayBerlinClock(LocalTime.now())
        ));
    }
}
