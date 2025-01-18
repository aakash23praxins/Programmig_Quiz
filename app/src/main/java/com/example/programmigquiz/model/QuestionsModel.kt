package com.example.programmigquiz.model

data class QuestionsModel(
    val questionId: Int,
    val questionText: String,
    val correctAnswer: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
)
