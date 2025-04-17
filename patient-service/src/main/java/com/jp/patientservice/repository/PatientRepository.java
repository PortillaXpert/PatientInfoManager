package com.jp.patientservice.repository;

import com.jp.patientservice.models.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Patient entities.
 * Extends JpaRepository to provide CRUD operations and database interaction.
 */
@Repository
@Schema(description = "Repository for managing Patient entities in the database.")
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    /**
     * Checks if a patient with the given email exists.
     * @param email The email to check.
     * @return True if a patient with this email exists, otherwise false.
     */
    @Schema(description = "Verifies if a patient with the given email already exists.")
    boolean existsByEmail(String email);
    /**
     * Checks if an email is already used by another patient excluding a specific ID.
     * @param email The email to check.
     * @param id The ID of the patient to exclude from the check.
     * @return True if the email is used by another patient, otherwise false.
     */
    @Schema(description = "Verifies if an email is used by another patient, excluding a specific ID.")
    boolean existsByEmailAndIdNot(String email, UUID id);
}
