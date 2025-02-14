package com.janitri.backend.controllers;

import com.janitri.backend.models.Patient;
import com.janitri.backend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Use DTO for cleaner input handling
    static class PatientRequest {
        public String name;
        public int age;
        public String disease;
        public Long doctorId;
    }

    @PostMapping("/register")
    public Patient addPatient(@RequestBody PatientRequest request) {
        return patientService.addPatient(
                request.name,
                request.age,
                request.disease,
                request.doctorId
        );
    }

    @GetMapping("/")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
}
}