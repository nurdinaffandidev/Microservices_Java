package com.nurdinaffandidev.QuizApp.question.exception;

/**
 * Custom exception thrown when a requested category is not found in the system.
 * This is a runtime (unchecked) exception.
 */
public class CategoryNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message Detailed message describing the error.
     */
    public CategoryNotFoundException(String message) {
        super(message); // Calls the superclass constructor with the provided message
    }
}
