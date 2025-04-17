package com.jp.patientservice.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;


/**
 * Entity representing a Patient in the system.
 * Uses JPA annotations for persistence and validation constraints to ensure data integrity.
 */
@Entity
@Schema(description = "Entity representing a patient in the system.")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unique identifier for the patient", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private UUID id;

    @NotNull
    @Schema(description = "Full name of the patient", example = "John Doe")
    private String name;

    @NotNull
    @Email
    @Column(unique = true)
    @Schema(description = "Email address of the patient, must be unique", example = "juanda@domain.com")
    private String email;

    @NotNull
    @Schema(description = "Residential address of the patient", example = "123 Main Street")
    private String address;

    @NotNull
    @Schema(description = "Date of birth in YYYY-MM-DD format", example = "1990-01-01")
    private LocalDate dateOfBirth;

    @NotNull
    @Schema(description = "Date when the patient was registered", example = "2025-04-17")
    private LocalDate registeredDate;


    /**
     * Getters and Setters
     */
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registerDate) {
        this.registeredDate = registerDate;
    }
}
