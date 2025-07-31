package com.nurdinaffandidev.QuizApp.quiz.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuizResponse {
    private Integer questionId;
    private String response;
}
