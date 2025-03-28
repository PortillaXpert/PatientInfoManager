package com.jp.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Data Transfer Object for handling patient registration requests.
 * Ensures validation constraints for input data integrity.
 */
public class PatientRequestDTO {
@NotBlank(message = "Name is required.")
@Size(max = 100, message = "Cannot exceed 100 characters.")
    private String name;

@NotBlank(message = "Email is required.")
@Email(message = "Email should be valid.")
    private String email;

@NotBlank(message = "Adress is required.")
    private String address;

@NotBlank(message = "Date of birth is required.")
    private String dateOfBirth;

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
