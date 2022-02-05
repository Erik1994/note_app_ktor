package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.entity.NoteEntity

interface DeleteNoteUseCase {
    suspend fun deleteNoteById(noteId: String)
    suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String)
}