package com.jp.patientservice.exception;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Custom exception for handling email duplication errors.
 */
@Schema(description = "Exception thrown when an email is already registered in the system.")
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
