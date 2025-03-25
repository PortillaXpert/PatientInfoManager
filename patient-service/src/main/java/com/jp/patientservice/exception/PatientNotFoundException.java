package com.jp.patientservice.exception;

/**
 * Exception thrown when a requested patient is not found.
 */
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
