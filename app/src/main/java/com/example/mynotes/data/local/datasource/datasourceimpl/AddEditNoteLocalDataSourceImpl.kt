package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.local.datasource.AddEditNoteLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao

class AddEditNoteLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
): AddEditNoteLocalDataSource {
}