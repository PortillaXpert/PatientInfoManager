package com.jp.patientservice.mapping;

import com.jp.patientservice.dto.PatientRequestDTO;
import com.jp.patientservice.dto.PatientResponseDTO;
import com.jp.patientservice.models.Patient;

import java.time.LocalDate;

/**
 * Utility class for mapping between Patient entity and DTOs.
 */
public class PatientMapper {

    /**
     * Converts a Patient entity to a PatientResponseDTO.
     * @param patient The Patient entity to convert.
     * @return The corresponding PatientResponseDTO.
     */
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
