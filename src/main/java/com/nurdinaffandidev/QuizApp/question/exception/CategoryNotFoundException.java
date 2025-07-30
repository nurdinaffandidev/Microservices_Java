package com.nurdinaffandidev.QuizApp.question.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message ) {
        super(message);
    }
}
