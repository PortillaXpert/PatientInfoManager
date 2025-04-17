package com.jp.patientservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI documentation.
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Patient API",
        version = "1.0",
        description = "API documentation for Patient Service"
))
public class OpenAPIConfig {
}
