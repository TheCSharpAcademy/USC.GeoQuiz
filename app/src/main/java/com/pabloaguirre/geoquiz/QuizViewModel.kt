package com.pabloaguirre.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var currentIndex: Int
        get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
        set(value) {
            savedStateHandle[CURRENT_INDEX_KEY] = value
        }
    private val answeredQuestions = BooleanArray(questionBank.size)
    private var correctAnswerCount = 0
    private var scoreShown = false

    var isCheater: Boolean
        get() = savedStateHandle[IS_CHEATER_KEY] ?: false
        set(value) {
            savedStateHandle[IS_CHEATER_KEY] = value
        }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    val canAnswerCurrentQuestion: Boolean
        get() = !answeredQuestions[currentIndex]

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size
    }

    fun answerCurrentQuestion(userAnswer: Boolean): Boolean {
        val isCorrect = userAnswer == currentQuestionAnswer
        if (canAnswerCurrentQuestion) {
            answeredQuestions[currentIndex] = true
            if (isCorrect) {
                correctAnswerCount++
            }
        }
        return isCorrect
    }

    fun takeScoreIfComplete(): Int? {
        if (!answeredQuestions.all { it } || scoreShown) {
            return null
        }
        scoreShown = true
        return correctAnswerCount * 100 / questionBank.size
    }
}
