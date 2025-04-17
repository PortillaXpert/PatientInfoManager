package com.jp.patientservice.dto;


import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for returning patient details in API responses.
 */
@Schema(description = "DTO for patient response data")
public class PatientResponseDTO {
    @Schema(description = "Unique identifier of the patient", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    private String id;

    @Schema(description = "Full name of the patient", example = "Juan David")
    private String name;

    @Schema(description = "Email address of the patient", example = "juanda@domain.com")
    private String email;

    @Schema(description = "Residential address of the patient", example = "123 Main Street, City")
    private String address;

    @Schema(description = "Date of birth of the patient in YYYY-MM-DD format", example = "1990-01-01")
    private String dateOfBirth;

    /**
     * Getters and Setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
