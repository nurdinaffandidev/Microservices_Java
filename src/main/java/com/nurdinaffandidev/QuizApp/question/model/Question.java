package com.nurdinaffandidev.QuizApp.question.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a question entity in the QuizApp system.
 * Each question includes a title, multiple choice options,
 * a correct answer, a difficulty level, and a category.
 */
@Entity // Marks this class as a JPA entity for ORM mapping
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Lombok: Generates a constructor with all fields
@NoArgsConstructor  // Lombok: Generates a no-argument constructor
public class Question {

    // Unique identifier for the question (Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented by the database
    private int id;

    // Category to which this question belongs (e.g., Java, Math)
    private String category;

    // Difficulty level of the question (e.g., Easy, Medium, Hard)
    private String difficulty;

    // First option for the question
    private String option1;

    // Second option for the question
    private String option2;

    // Third option for the question
    private String option3;

    // Fourth option for the question
    private String option4;

    // The actual question text/title shown to the user
    private String questionTitle;

    // The correct answer from among the options
    private String correctAnswer;
}
