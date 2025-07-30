package com.nurdinaffandidev.QuizApp.question.exception;

public class QuestionNotFoundException extends RuntimeException{
    public QuestionNotFoundException(String message) {
        super(message);
    }
}
