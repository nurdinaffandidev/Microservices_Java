package com.nurdinaffandidev.QuizApp.quiz.service;

import com.nurdinaffandidev.QuizApp.question.model.Question;
import com.nurdinaffandidev.QuizApp.question.model.QuestionWrapper;
import com.nurdinaffandidev.QuizApp.question.service.QuestionService;
import com.nurdinaffandidev.QuizApp.quiz.exception.QuizNotFoundException;
import com.nurdinaffandidev.QuizApp.quiz.model.Quiz;
import com.nurdinaffandidev.QuizApp.quiz.model.QuizResponse;
import com.nurdinaffandidev.QuizApp.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionService questionService;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuestionService questionService) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
    }

    /**
     * Creates a new quiz with random questions from a specified category.
     *
     * @param category     the category of questions to use
     * @param numQuestions the number of questions to include in the quiz
     * @param title        the title of the quiz
     * @return the created Quiz entity
     */
    public Quiz createQuiz(String category, Integer numQuestions, String title) {
        List<Question> questions = questionService.generateQuestions(category, numQuestions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return quiz;
    }

    /**
     * Retrieves all quizzes stored in the database.
     *
     * @return list of all quizzes
     */
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    /**
     * Retrieves a quiz's questions without exposing correct answers.
     *
     * @param id the quiz ID
     * @return list of QuestionWrapper objects (question + 4 options)
     * @throws QuizNotFoundException if quiz with the given ID is not found
     */
    public List<QuestionWrapper> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + id + " not found."));

        List<QuestionWrapper> cleanedQuestions = new ArrayList<>();
        quiz.getQuestions().forEach(question -> {
            QuestionWrapper cleanedQn = new QuestionWrapper(
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4(),
                    question.getQuestionTitle()
            );
            cleanedQuestions.add(cleanedQn);
        });
        return cleanedQuestions;
    }

    /**
     * Calculates the number of correct answers in a quiz submission.
     *
     * @param quizId    the ID of the quiz being submitted
     * @param responses list of user responses (questionId + selected answer)
     * @return total number of correct answers
     * @throws QuizNotFoundException if quiz with the given ID is not found
     */
    public Integer calculateResult(Integer quizId, List<QuizResponse> responses) {
        int correctAnswer = 0;

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException("Quiz with id= " + quizId + " not found."));

        List<Question> quizQuestions = quiz.getQuestions();

        for (QuizResponse response : responses) {
            Optional<Question> questionToCheck = quizQuestions.stream()
                    .filter(qn -> qn.getId() == response.getQuestionId())
                    .findFirst();

            if (questionToCheck.isPresent()) {
                if (response.getResponse().equals(questionToCheck.get().getCorrectAnswer())) {
                    correctAnswer++;
                }
            }
        }
        return correctAnswer;
    }
}
