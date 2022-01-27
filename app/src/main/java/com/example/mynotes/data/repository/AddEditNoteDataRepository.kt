package com.example.mynotes.data.repository

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface AddEditNoteDataRepository {
    fun addNote(note: NoteRequest): Flow<Resource<ResponseBody>>
    suspend fun insertNote(note: NoteEntity)
    fun getNoteById(id: String): Flow<SingleLiveEvent<Resource<NoteEntity>>>
    fun getEmail(): String
}