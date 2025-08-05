package com.nurdinaffandidev.QuizApp.question.exception;

/**
 * Custom exception thrown when a requested question is not found in the system.
 * This is an unchecked exception extending RuntimeException.
 */
public class QuestionNotFoundException extends RuntimeException {

    /**
     * Constructor that accepts a custom error message.
     *
     * @param message Detailed message describing the reason for the exception.
     */
    public QuestionNotFoundException(String message) {
        super(message); // Pass the error message to the parent RuntimeException constructor
    }
}