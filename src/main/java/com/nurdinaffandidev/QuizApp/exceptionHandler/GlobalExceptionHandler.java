package com.nurdinaffandidev.QuizApp.exceptionHandler;

import com.nurdinaffandidev.QuizApp.question.exception.CategoryNotFoundException;
import com.nurdinaffandidev.QuizApp.question.exception.QuestionNotFoundException;
import com.nurdinaffandidev.QuizApp.quiz.exception.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Global exception handler for the entire application.
 * Handles specific and generic exceptions and returns consistent API error responses.
 */
@RestControllerAdvice // Marks this class as a centralized exception handler for all controllers
public class GlobalExceptionHandler {

    /**
     * Handles CategoryNotFoundException and returns a 404 NOT FOUND error response.
     *
     * @param exception the exception thrown when a category is not found
     * @return ResponseEntity containing ApiError details
     */
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException exception) {
        // Create error response payload
        ApiError error = new ApiError(
                exception.getMessage(),               // Descriptive error message
                HttpStatus.NOT_FOUND.value(),         // 404 status code
                LocalDateTime.now()                   // Current timestamp
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles QuestionNotFoundException and returns a 404 NOT FOUND error response.
     *
     * @param exception the exception thrown when a question is not found
     * @return ResponseEntity containing ApiError details
     */
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ApiError> handleQuestionNotFound(QuestionNotFoundException exception) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles QuizNotFoundException and returns a 404 NOT FOUND error response.
     *
     * @param exception the exception thrown when a quiz is not found
     * @return ResponseEntity containing ApiError details
     */
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiError> handleQuizNotFound(QuizNotFoundException exception) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles any other unhandled exceptions (fallback).
     *
     * @param exception the generic exception thrown
     * @return ResponseEntity containing error message and 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        // Return generic error message for unhandled exceptions
        return new ResponseEntity<>(
                "Internal Server Error: " + exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
