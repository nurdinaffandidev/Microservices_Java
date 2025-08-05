package com.nurdinaffandidev.QuizApp.quiz.model;

import com.nurdinaffandidev.QuizApp.question.model.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity // Marks this class as a JPA entity mapped to a database table
@Data   // Lombok annotation to generate getters, setters, toString, equals, and hashCode
public class Quiz {

    // Primary key for the Quiz entity with auto-increment strategy
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Title of the quiz
    private String title;

    // Many-to-many relationship with questions
    // A quiz can contain many questions and a question can belong to many quizzes
    @ManyToMany
    private List<Question> questions;
}
