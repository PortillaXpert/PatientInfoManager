package com.jp.patientservice.controller;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST Controller for handling patient-related requests.
 */
@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Controller", description = "Handles patient management operations")
public class PatientController {

    private final PatientService patientService;

    /**
     * Constructor for injecting PatientService dependency.
     *
     * @param patientService Service layer handling patient business logic.
     */
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Retrieves a list of all patients.
     * @return ResponseEntity containing a list of PatientResponseDTO.
     */
    @GetMapping
    @Operation(
            summary = "Retrieve all patients",
            description = "Fetches a list of all patients from the system",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of patients retrieved successfully"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }


    @PostMapping
    @Operation(
            summary = "Create a new patient",
            description = "Registers a new patient in the system",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Patient successfully created"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Parameter(description = "Patient data to create", required = true)
            @Valid @RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update a patient",
            description = "Updates an existing patient's information",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Patient updated successfully"),
                    @ApiResponse(responseCode = "404", description = "Patient not found"),
                    @ApiResponse(responseCode = "400", description = "Invalid input data")
            }
    )
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @Parameter(description = "UUID of the patient to update", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479", required = true)
            @PathVariable UUID id,

            @Parameter(description = "Updated patient data", required = true)
            @RequestBody PatientRequestDTO patientRequestDTO) {

        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }


    /**
     * Deletes a patient by ID.
     * @param id The UUID of the patient to delete.
     * @return A ResponseEntity with no content if the deletion was successful.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a patient",
            description = "Removes a patient from the database using their unique ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Patient deleted successfully"),
                    @ApiResponse(responseCode = "404", description = "Patient not found")
            }
    )
    public ResponseEntity<Void> deletePatient(
            @Parameter(description = "UUID of the patient to delete", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
            @PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
