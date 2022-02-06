package com.example.mynotes.data.local.datasource

import com.example.mynotes.data.model.entity.LocallyDeletedNoteId
import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesLocalDataSource {
    fun deleteToken()
    fun getAllNotes(): Flow<List<NoteEntity>>
    suspend fun deleteAllNotes()
    suspend fun deleteNoteByID(noteId: String)
    suspend fun insertNotes(notes: List<NoteEntity>)
    suspend fun insertDeletedNoteId(locallyDeletedNoteId: LocallyDeletedNoteId)
    suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String)
    suspend fun getAllLocallyDeletedNoteIds(): List<LocallyDeletedNoteId>
    suspend fun getAllUnSyncedNotes(): List<NoteEntity>
}