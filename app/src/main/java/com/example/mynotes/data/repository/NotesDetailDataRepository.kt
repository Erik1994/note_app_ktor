package com.example.mynotes.data.repository

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface NotesDetailDataRepository {
    fun observeNoteById(noteId: String): Flow<NoteEntity>
    fun addOwnerToNode(addOwnerRequest: AddOwnerRequest): Flow<Resource<SimpleData>>
}