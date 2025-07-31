package com.nurdinaffandidev.QuizApp.quiz.repository;

import com.nurdinaffandidev.QuizApp.quiz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {
}
