package com.jp.patientservice.service;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.exception.EmailAlreadyExistsException;
import com.jp.patientservice.exception.PatientNotFoundException;
import com.jp.patientservice.mapping.PatientMapper;
import com.jp.patientservice.models.Patient;
import com.jp.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
     * @throws EmailAlreadyExistsException if the updated email is already in use.
     * @return PatientResponseDTO with the saved patient's details.
     */
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
    if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
        throw new EmailAlreadyExistsException("A patient with this email "
        + "Already exists: " + patientRequestDTO.getEmail());
    }
        Patient newPatient = patientRepository.save(
            PatientMapper.toModel(patientRequestDTO));

    return PatientMapper.toDTO(newPatient);
    }

    /**
     * Updates an existing patient's details.
     *
     * @param id The UUID of the patient to update.
     * @param patientRequestDTO The updated patient data.
     * @return A DTO containing the updated patient information.
     * @throws PatientNotFoundException if the patient ID does not exist.
     * @throws EmailAlreadyExistsException if the updated email is already in use.
     */
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with ID" + id));
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient with this email "
                    + "Already exists: " + patientRequestDTO.getEmail());
        }

        //Update patient details
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        // Save the updated patient entity
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }
}
