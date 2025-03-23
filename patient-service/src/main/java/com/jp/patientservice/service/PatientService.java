package com.jp.patientservice.service;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.mapping.PatientMapper;
import com.jp.patientservice.models.Patient;
import com.jp.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
    Patient newPatient = patientRepository.save(
            PatientMapper.toModel(patientRequestDTO));

    return PatientMapper.toDTO(newPatient);
    }
}
