package com.nurdinaffandidev.QuizApp.question.service;

import com.nurdinaffandidev.QuizApp.question.exception.CategoryNotFoundException;
import com.nurdinaffandidev.QuizApp.question.exception.QuestionNotFoundException;
import com.nurdinaffandidev.QuizApp.question.model.Question;
import com.nurdinaffandidev.QuizApp.question.repository.QuestionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle business logic related to Questions.
 */
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    /**
     * Constructor for dependency injection of QuestionRepository.
     *
     * @param questionRepository the repository used to access question data
     */
    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Retrieves all questions from the database.
     *
     * @return list of all questions
     */
    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    /**
     * Retrieves questions filtered by the specified category.
     * Throws CategoryNotFoundException if no questions are found.
     *
     * @param category the category to filter by
     * @return list of questions in the category
     * @throws CategoryNotFoundException when no questions exist in the given category
     */
    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        if (questions == null || questions.isEmpty()) {
            throw new CategoryNotFoundException("No questions found for category: " + category);
        }
        return questions;
    }

    /**
     * Retrieves a question by its ID.
     * Throws QuestionNotFoundException if question does not exist.
     *
     * @param id the question ID
     * @return the question with the specified ID
     * @throws QuestionNotFoundException when the question ID is not found
     */
    public Question getQuestionById(int id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id= " + id + " not found."));
    }

    /**
     * Adds a new question to the database.
     *
     * @param question the question entity to add (validated)
     * @return the saved question entity with generated ID
     */
    public Question addQuestion(@Valid Question question) {
        return questionRepository.save(question);
    }

    /**
     * Deletes a question by its ID.
     * Throws QuestionNotFoundException if the question does not exist.
     *
     * @param id the question ID to delete
     * @return the deleted question entity
     * @throws QuestionNotFoundException when the question ID is not found
     */
    public Question deleteQuestion(int id) {
        Question questionToDelete = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id= " + id + " not found."));
        questionRepository.deleteById(id);
        return questionToDelete;
    }

    /**
     * Generates a list of random questions for a specified category.
     * First fetches random question IDs, then retrieves full Question entities.
     *
     * @param category the category to filter questions by
     * @param numQuestions number of random questions to generate
     * @return list of randomly selected questions
     */
    public List<Question> generateQuestions(String category, int numQuestions) {
        // Retrieve random question IDs for the category
        List<Integer> questionsId = questionRepository.findRandomQuestionsByCategory(category, numQuestions);

        List<Question> questions = new ArrayList<>();
        // For each ID, fetch the full Question object
        for (int id : questionsId) {
            questions.add(getQuestionById(id));
        }

        return questions;
    }
}
