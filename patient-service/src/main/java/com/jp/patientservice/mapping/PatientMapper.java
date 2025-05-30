package com.jp.patientservice.mapping;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.models.Patient;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

/**
 * Utility class for mapping between Patient entity and DTOs.
 */
@Schema(description = "Utility class for converting between Patient entity and DTOs.")
public class PatientMapper {

    /**
     * Converts a Patient entity to a PatientResponseDTO.
     * @param patient The Patient entity to convert.
     * @return The corresponding PatientResponseDTO.
     */
    @Schema(description = "Transforms a Patient entity into a PatientResponseDTO representation.")
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientDTO;
    }

    /**
     * Converts a PatientRequestDTO to a Patient entity.
     * @param patientRequestDTO The DTO containing patient data.
     * @return The corresponding Patient entity.
     */
    @Schema(description = "Transforms a PatientRequestDTO into a Patient entity representation.")
    public static Patient toModel(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }

}
