package com.pabloaguirre.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val ANSWER_SHOWN_KEY = "ANSWER_SHOWN_KEY"

class CheatViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    var isAnswerShown: Boolean
        get() = savedStateHandle[ANSWER_SHOWN_KEY] ?: false
        set(value) {
            savedStateHandle[ANSWER_SHOWN_KEY] = value
        }
}
