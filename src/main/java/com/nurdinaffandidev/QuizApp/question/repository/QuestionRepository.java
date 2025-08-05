package com.nurdinaffandidev.QuizApp.question.repository;

import com.nurdinaffandidev.QuizApp.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for Question entity operations.
 * Extends JpaRepository to provide standard CRUD methods.
 */
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    /**
     * Finds all questions that belong to the specified category.
     *
     * @param category the category to filter questions by
     * @return a list of questions within the given category
     */
    List<Question> findByCategory(String category);

    /**
     * Finds a specified number of random question IDs from the given category.
     * Uses a native SQL query with ORDER BY RANDOM() and LIMIT.
     *
     * @param category the category to filter questions by
     * @param numQuestions the number of random question IDs to retrieve
     * @return a list of question IDs randomly selected from the category
     */
    @Query(value = "SELECT q.id FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQuestions", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQuestions);
}
