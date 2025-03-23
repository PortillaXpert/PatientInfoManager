package com.jp.patientservice.service;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.mapping.PatientMapper;
import com.jp.patientservice.models.Patient;
import com.jp.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling business logic related to Patient operations.
 */
@Service
public class PatientService {
    private PatientRepository patientRepository;

    /**
     * Constructor for dependency injection.
     * @param patientRepository Repository for managing patient data.
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieves all patients from the database and converts them to DTOs.
     * @return List of PatientResponseDTO objects.
     */
    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }

    /**
     * Creates a new patient entity from the provided DTO, saves it, and returns the response DTO.
     * @param patientRequestDTO DTO containing patient details.
     * @return PatientResponseDTO with the saved patient's details.
     */
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
    Patient newPatient = patientRepository.save(
            PatientMapper.toModel(patientRequestDTO));

    return PatientMapper.toDTO(newPatient);
    }
}
