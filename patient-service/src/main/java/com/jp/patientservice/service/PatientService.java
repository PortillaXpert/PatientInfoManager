package com.jp.patientservice.service;

import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.models.Patient;
import com.jp.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
    }
}
