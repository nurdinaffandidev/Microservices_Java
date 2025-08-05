package com.nurdinaffandidev.QuizApp;

import com.nurdinaffandidev.QuizApp.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * This component loads initial data into the application on startup.
 * It creates a sample quiz when the Spring Boot application starts.
 */
@Component
public class DataLoader implements ApplicationRunner {

    private final QuizService quizService;

    /**
     * Constructor-based dependency injection for QuizService.
     *
     * @param quizService the QuizService used to create a quiz
     */
    @Autowired
    public DataLoader(QuizService quizService) {
        this.quizService = quizService;
    }

    /**
     * This method is executed after the application context is loaded and right before the Spring Boot app runs.
     * It creates a quiz with 5 Java questions titled "Java Quiz 1".
     *
     * @param args incoming application arguments
     * @throws Exception if any error occurs during quiz creation
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Create a quiz with 5 random questions from the "Java" category
        quizService.createQuiz("Java", 5, "Java Quiz 1");

        // Log confirmation to console
        System.out.println("DataLoader run complete");
    }
}
