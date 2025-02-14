package com.janitri.backend.services;

import com.janitri.backend.models.Patient;
import com.janitri.backend.models.User;
import com.janitri.backend.repositories.PatientRepository;
import com.janitri.backend.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    public Patient addPatient(String name, int age, String disease, Long doctorId) {
        User doctor = userRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with ID " + doctorId + " not found"));

        Patient patient = new Patient(name, age, disease, doctor);
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
}
}

