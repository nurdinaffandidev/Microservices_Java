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

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAllQuestion() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        List<Question> questions = questionRepository.findByCategory(category);
        if (questions == null || questions.isEmpty()) {
            throw new CategoryNotFoundException("No questions found for category: " + category);
        }
        return questions;
    }

    public Question getQuestionById(int id) {
        return questionRepository.findById(id).
                orElseThrow(() -> new QuestionNotFoundException("Question with id= " + id + " not found."));
    }

    public Question addQuestion(@Valid Question question) {
        return questionRepository.save(question);
    }

    public Question deleteQuestion(int id) {
        Question questionToDelete = questionRepository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundException("Question with id= " + id + " not found."));
        questionRepository.deleteById(id);
        return questionToDelete;
    }

    public List<Question> generateQuestions(String category, int numQuestions) {
        List<Integer> questionsId = questionRepository.findRandomQuestionsByCategory(category, numQuestions);
        List<Question> questions = new ArrayList<>();

        for(int id : questionsId) {
            questions.add(getQuestionById(id));
        }

        return questions;
    }
}
