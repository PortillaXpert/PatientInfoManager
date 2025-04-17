package com.jp.patientservice.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Exception thrown when a requested patient is not found.
 */
@Schema(description = "Exception thrown when the patient ID is not found in the system.")
public class PatientNotFoundException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message The detail message explaining the error.
     */
    public PatientNotFoundException(String message) {
        super(message);
    }
}
