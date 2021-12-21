package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.put
import com.example.mynotes.data.local.TOKEN_KEY
import com.example.mynotes.data.local.datasource.NotesLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao

class NotesLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : NotesLocalDataSource {
    override fun deleteToken() = sharedPreferences.put(TOKEN_KEY, "")
}