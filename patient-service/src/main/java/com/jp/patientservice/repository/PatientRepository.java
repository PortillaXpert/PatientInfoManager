package com.jp.patientservice.repository;

import com.jp.patientservice.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for managing Patient entities.
 * Extends JpaRepository to provide CRUD operations and database interaction.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    boolean existsByEmail(String email);
}
