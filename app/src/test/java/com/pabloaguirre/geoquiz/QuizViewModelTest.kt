package com.pabloaguirre.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class QuizViewModelTest {

    @Test
    fun providesExpectedQuestionText() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)

        assertEquals(
            R.string.question_australia,
            quizViewModel.currentQuestionText
        )
    }

    @Test
    fun wrapsAroundQuestionBank() {
        val savedStateHandle = SavedStateHandle(
            mapOf(CURRENT_INDEX_KEY to 5)
        )
        val quizViewModel = QuizViewModel(savedStateHandle)

        assertEquals(
            R.string.question_asia,
            quizViewModel.currentQuestionText
        )

        quizViewModel.moveToNext()

        assertEquals(
            R.string.question_australia,
            quizViewModel.currentQuestionText
        )
    }

    @Test
    fun providesTrueAnswerForAustraliaQuestion() {
        val quizViewModel = QuizViewModel(SavedStateHandle())

        assertTrue(quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun providesFalseAnswerForMideastQuestion() {
        val savedStateHandle = SavedStateHandle(
            mapOf(CURRENT_INDEX_KEY to 2)
        )
        val quizViewModel = QuizViewModel(savedStateHandle)

        assertFalse(quizViewModel.currentQuestionAnswer)
    }

    @Test
    fun tracksCheatingForEachQuestionSeparately() {
        val savedStateHandle = SavedStateHandle()
        val quizViewModel = QuizViewModel(savedStateHandle)

        quizViewModel.isCheater = true
        quizViewModel.moveToNext()

        assertFalse(quizViewModel.isCheater)

        quizViewModel.moveToPrevious()

        assertTrue(quizViewModel.isCheater)
    }

    @Test
    fun preservesPerQuestionCheatingInSavedState() {
        val savedStateHandle = SavedStateHandle()
        QuizViewModel(savedStateHandle).isCheater = true

        val restoredViewModel = QuizViewModel(savedStateHandle)

        assertTrue(restoredViewModel.isCheater)
        restoredViewModel.moveToNext()
        assertFalse(restoredViewModel.isCheater)
    }
}
