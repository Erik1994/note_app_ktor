package com.example.mynotes.data.repository

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface NotesDataRepository {
    fun deleteToken()
    fun getAllNotes(): Flow<Resource<List<NoteEntity>>>
}