package com.nurdinaffandidev.QuizApp.question.controller;

import com.nurdinaffandidev.QuizApp.question.model.Question;
import com.nurdinaffandidev.QuizApp.question.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marks this class as a REST controller (returns JSON/XML responses)
@RequestMapping("quiz-app/question") // Base URI path for all endpoints in this controller
public class QuestionController {

    private final QuestionService questionService;

    @Autowired // Injects the QuestionService dependency via constructor
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Greeting endpoint – accessed via GET /quiz-app/question/
     * Only matched when no 'id' query parameter is present.
     */
    @GetMapping(value = "/", params = "!id")
    public String greet(HttpServletRequest request) {
        return "Welcome To QuizApp Question Controller";
    }

    /**
     * Get all questions.
     * Endpoint: GET /quiz-app/question/allQuestions
     */
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        // Returns all questions with HTTP 200 OK
        return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
    }

    /**
     * Get questions filtered by category.
     * Endpoint: GET /quiz-app/question/category/{category}
     *
     * @param category the category of questions to retrieve
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    /**
     * Get question by ID.
     * This endpoint is only called when the request has an 'id' query param.
     * Endpoint: GET /quiz-app/question/?id=1
     *
     * @param id the question ID
     */
    @GetMapping(value = "/", params = "id")
    public ResponseEntity<Question> getQuestionById(@RequestParam int id) {
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    /**
     * Add a new question.
     * Endpoint: POST /quiz-app/question/add-question
     *
     * @param question the question object in JSON body
     */
    @PostMapping("/add-question")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Question question) {
        // Validates and adds the question
        Question addedQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>("Question successfully added, id: " + addedQuestion.getId(), HttpStatus.CREATED);
    }

    /**
     * Delete a question by ID.
     * Endpoint: DELETE /quiz-app/question/{id}
     *
     * @param id the question ID to delete
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id) {
        Question questionToDelete = questionService.deleteQuestion(id);
        return new ResponseEntity<>(questionToDelete, HttpStatus.OK);
    }

    /**
     * Generate random questions from a specific category.
     * Endpoint: GET /quiz-app/question/generate?category=Java&numQuestions=5
     *
     * @param category the category of questions
     * @param numQuestions number of questions to generate
     */
    @GetMapping("/generate")
    public ResponseEntity<List<Question>> generateQuestion(
            @RequestParam String category,
            @RequestParam int numQuestions
    ) {
        return new ResponseEntity<>(questionService.generateQuestions(category, numQuestions), HttpStatus.OK);
    }
}
