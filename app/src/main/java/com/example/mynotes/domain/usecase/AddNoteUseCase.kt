package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.request.NoteRequest

interface AddNoteUseCase {
    operator fun invoke(note: NoteRequest)
}