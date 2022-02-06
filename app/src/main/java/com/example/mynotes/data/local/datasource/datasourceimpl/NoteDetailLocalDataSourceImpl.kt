package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao
import com.example.mynotes.data.model.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteDetailLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : NoteDetailLocalDataSource {
    override fun observeNoteById(noteId: String): Flow<NoteEntity> =
        noteDao.observeNoteById(noteId)
}