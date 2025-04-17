package com.jp.patientservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for handling patient registration requests.
 * Ensures validation constraints for input data integrity.
 */
@Schema(description = "DTO for patient registration requests")
public class PatientRequestDTO {

@Schema(description = "Full name of the patient", example = "Juan Rodriguez")
@NotBlank(message = "Name is required.")
@Size(max = 100, message = "Cannot exceed 100 characters.")
    private String name;

@Schema(description = "Email address of the patient", example = "juanro@gmail.com")
@NotBlank(message = "Email is required.")
@Email(message = "Email should be valid.")
    private String email;

@Schema(description = "Residential address of the patient", example = "123 Main Street")
@NotBlank(message = "Adress is required.")
    private String address;

@Schema(description = "Date of birth of the patient in YYYY-MM-DD format", example = "1990-01-01")
@NotBlank(message = "Date of birth is required.")
    private String dateOfBirth;

@Schema(description = "Registration date of the patient in YYYY-MM-DD format", example = "2025-04-17")
@NotBlank(message = "Registered date is required.")
    private String registeredDate;


    /**
     * Getters and Setters
     */
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

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
