package com.janitri.backend.controllers;
import com.janitri.backend.models.HeartRate;
import com.janitri.backend.services.HeartRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients/{patientId}/heart-rate")
public class HeartRateController {

    @Autowired
    private HeartRateService heartRateService;

    @PostMapping
    public ResponseEntity<HeartRate> recordHeartRate(@PathVariable Long patientId, @RequestParam int bpm) {
        HeartRate heartRate = heartRateService.recordHeartRate(patientId, bpm);
        return ResponseEntity.ok(heartRate);
    }

    @GetMapping
    public ResponseEntity<List<HeartRate>> getHeartRateHistory(@PathVariable Long patientId) {
        List<HeartRate> heartRateRecords = heartRateService.getHeartRateHistory(patientId);
        return ResponseEntity.ok(heartRateRecords);
}
}


