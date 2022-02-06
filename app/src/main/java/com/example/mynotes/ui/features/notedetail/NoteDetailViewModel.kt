package com.example.mynotes.ui.features.notedetail

import com.example.mynotes.domain.usecase.ObserveNoteByIdUseCase
import com.example.mynotes.ui.common.BaseViewModel

class NoteDetailViewModel(private val observeNoteByIdUseCase: ObserveNoteByIdUseCase) :
    BaseViewModel() {
    fun observeNoteById(noteId: String) = observeNoteByIdUseCase(noteId)
}