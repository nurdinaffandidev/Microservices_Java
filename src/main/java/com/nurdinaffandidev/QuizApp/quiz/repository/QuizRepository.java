package com.nurdinaffandidev.QuizApp.quiz.repository;

import com.nurdinaffandidev.QuizApp.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link Quiz} entity.
 * Extends {@link JpaRepository} to provide CRUD operations and more for the Quiz table.
 */
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // No custom methods required for now — JpaRepository provides all basic CRUD functionality.
}
