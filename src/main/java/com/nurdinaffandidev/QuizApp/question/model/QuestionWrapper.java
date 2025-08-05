package com.nurdinaffandidev.QuizApp.question.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A simplified wrapper class for a Question object.
 * This is used to expose only the necessary fields when sending questions
 * to clients (e.g., without revealing the correct answer).
 */
@Data // Lombok: Automatically generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Lombok: Generates a constructor with all fields
public class QuestionWrapper {

    // First option for the question
    private String option1;

    // Second option for the question
    private String option2;

    // Third option for the question
    private String option3;

    // Fourth option for the question
    private String option4;

    // The actual question text shown to the user
    private String questionTitle;
}
