package com.example.mynotes.data.repository

import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesDetailDataRepository {
    fun observeNoteById(noteId: String): Flow<NoteEntity>
}