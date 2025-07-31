package com.nurdinaffandidev.QuizApp;

import com.nurdinaffandidev.QuizApp.quiz.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final QuizService quizService;

    @Autowired
    public DataLoader(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        quizService.createQuiz("Java", 5, "Java Quiz 1");
        System.out.println("DataLoader run complete");
    }
}
