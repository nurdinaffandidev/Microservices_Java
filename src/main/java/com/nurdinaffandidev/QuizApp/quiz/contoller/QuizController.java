package com.nurdinaffandidev.QuizApp.quiz.contoller;

import com.nurdinaffandidev.QuizApp.question.model.QuestionWrapper;
import com.nurdinaffandidev.QuizApp.question.service.QuestionService;
import com.nurdinaffandidev.QuizApp.quiz.model.Quiz;
import com.nurdinaffandidev.QuizApp.quiz.model.QuizDTO;
import com.nurdinaffandidev.QuizApp.quiz.model.QuizResponse;
import com.nurdinaffandidev.QuizApp.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller that handles all quiz-related HTTP endpoints.
 */
@RestController
@RequestMapping("quiz-app/quiz")
public class QuizController {

    private final QuizService quizService;
    private final QuestionService questionService;

    /**
     * Constructor-based injection for quiz and question services.
     */
    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    /**
     * Endpoint to retrieve all quizzes from the database.
     *
     * @return ResponseEntity containing a list of quizzes
     */
    @GetMapping("/allQuizzes")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return new ResponseEntity<>(quizService.getAllQuizzes(), HttpStatus.OK);
    }

    /**
     * Endpoint to create a new quiz.
     * Takes a QuizDTO object containing category name, number of questions, and quiz title.
     *
     * @param quizDTO Data transfer object containing quiz setup details
     * @return ResponseEntity containing the created Quiz
     */
    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(
                quizService.createQuiz(
                        quizDTO.getCategoryName(), // category to filter questions
                        quizDTO.getNumQuestions(), // number of questions
                        quizDTO.getTitle()         // quiz title
                ),
                HttpStatus.OK
        );
    }

    /**
     * Endpoint to retrieve questions for a specific quiz.
     *
     * @param id the quiz ID
     * @return ResponseEntity containing a list of question wrappers
     */
    @GetMapping(value = "/", params = "id")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@RequestParam int id) {
        return new ResponseEntity<>(quizService.getQuizQuestions(id), HttpStatus.OK);
    }

    /**
     * Endpoint to submit answers and calculate the quiz result.
     *
     * @param quizId the ID of the quiz being submitted
     * @param responses list of user responses
     * @return ResponseEntity containing the quiz score (as an integer)
     */
    @PostMapping(value = "/submit/", params = "quizId")
    public ResponseEntity<Integer> submitAnswer(@RequestParam Integer quizId , @RequestBody List<QuizResponse> responses) {
        return new ResponseEntity<>(quizService.calculateResult(quizId, responses), HttpStatus.OK);
    }
}
