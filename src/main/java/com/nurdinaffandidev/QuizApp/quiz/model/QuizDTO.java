package com.nurdinaffandidev.QuizApp.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    String categoryName;
    Integer numQuestions;
    String title;
}
