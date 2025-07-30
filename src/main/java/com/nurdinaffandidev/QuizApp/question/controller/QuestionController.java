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

@RestController
@RequestMapping("quiz-app")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/question", params = "!id")
    public String greet(HttpServletRequest request) {
        return "Welcome To QuizApp Question Controller";
    }

    // get all questions
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return new ResponseEntity<>(questionService.getAllQuestion(), HttpStatus.OK);
    }

    // get questions by category
    @GetMapping("/question/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    // get question by id
    @GetMapping(value = "/question", params = "id")
    public ResponseEntity<Question> getQuestionById(@RequestParam int id) {
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    // add question
    @PostMapping("/question/add-question")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Question question) {
        Question addedQuestion = questionService.addQuestion(question);
        return new ResponseEntity<>("Question successfully added, id: " + addedQuestion.getId(), HttpStatus.CREATED);
    }

    // delete question
    @DeleteMapping("/question/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id) {
        Question questionToDelete = questionService.deleteQuestion(id);
        return new ResponseEntity<>(questionToDelete, HttpStatus.OK);
    }

    // generate questions
    @GetMapping("/question/generate")
    public ResponseEntity<List<Question>> generateQuestion(@RequestParam String category, @RequestParam int numQuestions) {
        return new ResponseEntity<>(questionService.generateQuestions(category, numQuestions), HttpStatus.OK);
    }
}
