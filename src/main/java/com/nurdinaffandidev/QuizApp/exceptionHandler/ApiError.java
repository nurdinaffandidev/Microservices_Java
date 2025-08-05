package com.nurdinaffandidev.QuizApp.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Represents a standard structure for API error responses.
 * Used to return consistent error information to clients.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor  // Lombok annotation to generate a default no-argument constructor
public class ApiError {

    /**
     * A human-readable message describing the error.
     * Example: "Resource not found", "Invalid input", etc.
     */
    private String message;

    /**
     * The HTTP status code associated with the error.
     * Example: 404 for Not Found, 400 for Bad Request.
     */
    private int status;

    /**
     * The timestamp when the error occurred.
     * Helps in debugging and correlating logs.
     */
    private LocalDateTime timestamp;
}
