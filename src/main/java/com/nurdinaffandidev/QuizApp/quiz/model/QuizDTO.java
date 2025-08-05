package com.nurdinaffandidev.QuizApp.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode methods
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-argument constructor
public class QuizDTO {

    // Name of the question category to fetch questions from
    String categoryName;

    // Number of questions to include in the quiz
    Integer numQuestions;

    // Title of the quiz
    String title;
}
