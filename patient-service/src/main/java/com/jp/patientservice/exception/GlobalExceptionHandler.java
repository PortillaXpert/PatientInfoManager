package com.jp.patientservice.exception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Handles validation errors",
            description = "Intercepts invalid request parameters and returns a structured error response",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Validation error",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    schema = @Schema(implementation = Map.class)))
            }
    )
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        // Extracts field-specific error messages from validation exception
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    @Operation(
            summary = "Handles duplicate email errors",
            description = "Throws an error when an email is already registered",
            responses = {
                    @ApiResponse(responseCode = "400", description = "Email already exists",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    schema = @Schema(implementation = Map.class)))
            }
    )
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException ex) {
        // Log a warning when an email duplication occurs (remove in production if needed)
        log.warn("Email address already exists{}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("email", "Email address already exists");
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(PatientNotFoundException.class)
    @Operation(
            summary = "Handles patient not found errors",
            description = "Returns an error response when a patient ID is not found",
            responses = {
                    @ApiResponse(responseCode = "404", description = "Patient not found",
                            content = @io.swagger.v3.oas.annotations.media.Content(
                                    schema = @Schema(implementation = Map.class)))
            }
    )
    public ResponseEntity<Map<String, String>> handlePatientNotFoundException(
            PatientNotFoundException ex){
        // Log a warning when an email duplication occurs (remove in production if needed)
        log.warn("Patient not found{}",ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        errors.put("patient", "Patient not found");
        return ResponseEntity.badRequest().body(errors);
    }

}
