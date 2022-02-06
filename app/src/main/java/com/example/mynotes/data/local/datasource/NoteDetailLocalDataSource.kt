package com.example.mynotes.data.local.datasource

import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteDetailLocalDataSource {
    fun observeNoteById(noteId: String): Flow<NoteEntity>
}