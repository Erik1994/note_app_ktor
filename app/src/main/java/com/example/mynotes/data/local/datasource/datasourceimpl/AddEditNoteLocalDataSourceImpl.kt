package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.get
import com.example.mynotes.data.local.EMAIL_KEY
import com.example.mynotes.data.local.datasource.AddEditNoteLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao
import com.example.mynotes.data.model.entity.NoteEntity

class AddEditNoteLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : AddEditNoteLocalDataSource {
    override suspend fun insertNote(note: NoteEntity) = noteDao.insertNote(note)
    override suspend fun getNoteById(id: String): NoteEntity? = noteDao.getNoteById(id)
    override fun getEmail(): String = sharedPreferences.get(EMAIL_KEY, "")
}