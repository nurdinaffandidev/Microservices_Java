package com.nurdinaffandidev.QuizApp.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@RequiredArgsConstructor // Generates a constructor for final or @NonNull fields (not used here since there are none)
public class QuizResponse {

    // ID of the question being answered
    private Integer questionId;

    // User's selected answer for the question
    private String response;
}
