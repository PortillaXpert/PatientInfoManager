package com.jp.patientservice.controller;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.service.PatientService;
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
     *
     * @return ResponseEntity containing a list of PatientResponseDTO.
     */
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    /**
     * Creates a new patient record.
     * @param patientRequestDTO The request body containing patient details.
     * @return ResponseEntity containing the created PatientResponseDTO.
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    /**
     * Updates an existing patient's information.
     *
     * @param id The UUID of the patient to update.
     * @param patientRequestDTO The updated patient data.
     * @return The updated patient details wrapped in a ResponseEntity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,
    @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO patientResponseDTO = patientService.updatePatient(id,
                patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }
}
