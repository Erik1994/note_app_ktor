package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface ObserveNoteByIdUseCase {
    operator fun invoke(noteId: String): Flow<NoteEntity>
}