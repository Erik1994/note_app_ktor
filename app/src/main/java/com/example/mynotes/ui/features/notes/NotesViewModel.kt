package com.example.mynotes.ui.features.notes

import com.example.mynotes.domain.usecase.DeleteTokenUseCase
import com.example.mynotes.ui.common.BaseViewModel

class NotesViewModel(private val deleteTokenUseCase: DeleteTokenUseCase): BaseViewModel() {
    fun deleteToken() = deleteTokenUseCase()
}