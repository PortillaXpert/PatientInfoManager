package com.jp.patientservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to manage application-wide exceptions.
 * This class intercepts exceptions and provides meaningful responses.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    // Remove this line in production if you don't need:
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        /**
         * Handles validation exceptions when request parameters fail validation.
         *
         * @param ex the exception triggered due to invalid method arguments
         * @return a ResponseEntity containing a map of validation errors
         */
        Map<String, String> errors = new HashMap<>();
        // Extracts field-specific error messages from validation exception
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles email duplication exceptions.
     *
     * @param ex the exception triggered when an email already exists
     * @return a ResponseEntity containing a standardized error response
     */
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex) {
        // Log a warning when an email duplication occurs (remove in production if needed)
        log.warn("Email address already exists{}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles exceptions when a patient is not found.
     *
     * @param ex The exception triggered when a patient ID is not found.
     * @return A ResponseEntity containing an error message.
     */
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(
            PatientNotFoundException ex){
        // Log a warning when an email duplication occurs (remove in production if needed)
        log.warn("Patient not found{}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("patient", "Patient not found");
        return ResponseEntity.badRequest().body(errors);
    }

}
