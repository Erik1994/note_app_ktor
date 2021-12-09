package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao

class NoteDetailLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : NoteDetailLocalDataSource {
}