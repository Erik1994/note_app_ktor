package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.put
import com.example.mynotes.data.local.TOKEN_KEY
import com.example.mynotes.data.local.datasource.NotesLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao
import com.example.mynotes.data.model.entity.LocallyDeletedNoteId
import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NotesLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : NotesLocalDataSource {
    override fun deleteToken() = sharedPreferences.put(TOKEN_KEY, "")

    override fun getAllNotes(): Flow<List<NoteEntity>> = noteDao.getAllNotes()

    override suspend fun insertNotes(notes: List<NoteEntity>) = noteDao.insertNotes(notes)

    override suspend fun insertDeletedNoteId(locallyDeletedNoteId: LocallyDeletedNoteId) =
        noteDao.insertDeletedNoteId(locallyDeletedNoteId)

    override suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String) =
        noteDao.deleteLocallyDeletedNoteId(deletedNoteId)

    override suspend fun getAllLocallyDeletedNoteIds(): List<LocallyDeletedNoteId> =
        noteDao.getAllLocallyDeletedNoteIds()

    override suspend fun deleteNoteByID(noteId: String) = noteDao.deleteNoteByID(noteId)

    override suspend fun getAllUnSyncedNotes(): List<NoteEntity> = noteDao.getAllUnSyncedNotes()
}