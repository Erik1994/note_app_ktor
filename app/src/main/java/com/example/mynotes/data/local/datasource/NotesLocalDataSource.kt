package com.example.mynotes.data.local.datasource

import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {
    fun deleteToken()
    fun getAllNotes(): Flow<List<NoteEntity>>
    suspend fun insertNotes(notes: List<NoteEntity>)
}