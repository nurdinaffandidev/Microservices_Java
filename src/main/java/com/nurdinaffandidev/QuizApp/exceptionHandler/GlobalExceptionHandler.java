package com.nurdinaffandidev.QuizApp.exceptionHandler;

import com.nurdinaffandidev.QuizApp.question.exception.CategoryNotFoundException;
import com.nurdinaffandidev.QuizApp.question.exception.QuestionNotFoundException;
import com.nurdinaffandidev.QuizApp.quiz.exception.QuizNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ApiError> handleCategoryNotFound(CategoryNotFoundException exception) {
        ApiError error = new ApiError(
                            exception.getMessage(),
                            HttpStatus.NOT_FOUND.value(),
                            LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ApiError> handleQuestionNotFound(QuestionNotFoundException exception) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception exception) {
        return new ResponseEntity<>("Internal Server Error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiError> handleQuizNotFound(QuizNotFoundException exception) {
        ApiError error = new ApiError(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND );
    }
}
