package com.nurdinaffandidev.QuizApp.quiz.exception;

/**
 * Custom exception to be thrown when a quiz is not found.
 * Extends RuntimeException to allow Spring to handle it as an unchecked exception.
 */
public class QuizNotFoundException extends RuntimeException {

    /**
     * Constructor that takes a custom error message.
     *
     * @param message The error message to be associated with the exception.
     */
    public QuizNotFoundException(String message) {
        super(message); // Pass the message to the parent RuntimeException
    }
}
