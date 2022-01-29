package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.entity.NoteEntity

interface InsertNoteUseCase {
    suspend operator fun invoke(note: NoteEntity)
}