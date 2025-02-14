package com.janitri.backend.services;
import com.janitri.backend.models.HeartRate;
import com.janitri.backend.models.Patient;
import com.janitri.backend.repositories.HeartRateRepository;
import com.janitri.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HeartRateService {

    @Autowired
    private HeartRateRepository heartRateRepository;

    @Autowired
    private PatientRepository patientRepository;

    public HeartRate recordHeartRate(Long patientId, int bpm) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        HeartRate heartRate = new HeartRate();
        heartRate.setPatient(patient);
        heartRate.setBpm(bpm);
        heartRate.setRecordedAt(LocalDateTime.now());

        return heartRateRepository.save(heartRate);
    }

    public List<HeartRate> getHeartRateHistory(Long patientId) {
        return heartRateRepository.findByPatientId(patientId);
}
}


