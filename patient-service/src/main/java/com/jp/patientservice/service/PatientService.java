package com.jp.patientservice.service;

import billing.BillingServiceGrpc;
import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.exception.EmailAlreadyExistsException;
import com.jp.patientservice.exception.PatientNotFoundException;
import com.jp.patientservice.grpc.BillingServiceGrpcClient;
import com.jp.patientservice.mapping.PatientMapper;
import com.jp.patientservice.models.Patient;
import com.jp.patientservice.repository.PatientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    /**
     * Constructor for dependency injection.
     * @param patientRepository Repository for managing patient data.
     */
    public PatientService(PatientRepository patientRepository, BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
    }

    /**
     * Retrieves all patients from the database and converts them to DTOs.
     * @return List of PatientResponseDTO objects.
     */
    @Operation(
            summary = "Get all patients",
            description = "Returns a list of all registered patients",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved list"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
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
    @Operation(
            summary = "Create a new patient",
            description = "Registers a new patient in the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Patient successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid patient data")
            }
    )
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
    if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
        throw new EmailAlreadyExistsException("A patient with this email "
        + "Already exists: " + patientRequestDTO.getEmail());
    }
        Patient newPatient = patientRepository.save(
            PatientMapper.toModel(patientRequestDTO));

    billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(), newPatient.getName(), newPatient.getEmail());
    return PatientMapper.toDTO(newPatient);
    }

    /**
     * Updates an existing patient's details.
     * @param id The UUID of the patient to update.
     * @param patientRequestDTO The updated patient data.
     * @return A DTO containing the updated patient information.
     */
    @Operation(
            summary = "Update patient details",
            description = "Updates the information of an existing patient",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated"),
                    @ApiResponse(responseCode = "404", description = "Patient not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid update data")
            }
    )
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient not found with ID" + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
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

    /**
     * Deletes a patient from the database by their unique ID.
     * @param id The UUID of the patient to be deleted.
     */
    @Operation(
            summary = "Delete a patient",
            description = "Removes a patient from the database using their ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Patient not found")
            }
    )
    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }
}
