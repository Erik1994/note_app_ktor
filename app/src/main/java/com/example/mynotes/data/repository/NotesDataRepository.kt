package com.example.mynotes.data.repository

import com.example.mynotes.data.model.entity.LocallyDeletedNoteId
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface NotesDataRepository {
    fun deleteToken()
    fun getAllNotes(): Flow<Resource<List<NoteEntity>>>
    suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String)
    suspend fun getAllLocallyDeletedNoteIds(): List<LocallyDeletedNoteId>
    suspend fun getAllUnSyncedNotes(): List<NoteEntity>
    suspend fun insertDeletedNoteId(locallyDeletedNoteId: LocallyDeletedNoteId)
    suspend fun insertNotes(notes: List<NoteEntity>)
    suspend fun deleteNoteById(noteId: String)
}