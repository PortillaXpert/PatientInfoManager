package com.jp.patientservice.exception;

/**
 * Custom exception for handling email duplication errors.
 */
public class EmailAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message explaining the error
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
