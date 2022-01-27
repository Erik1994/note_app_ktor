package com.example.mynotes.data.local.datasource

import com.example.mynotes.data.model.entity.NoteEntity

interface AddEditNoteLocalDataSource {
    suspend fun insertNote(note: NoteEntity)
    suspend fun getNoteById(id: String): NoteEntity?
    fun getEmail(): String
}