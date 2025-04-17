package com.jp.patientservice;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Patient Service application.
 * Bootstraps the Spring Boot application.
 */
@SpringBootApplication
@Schema(description = "Main entry point for the Patient Service application.")
public class PatientServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientServiceApplication.class, args);
	}

}
