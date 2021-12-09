package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.local.datasource.AuthLocalDataSource
import com.example.mynotes.data.local.db.dao.NoteDao

class AuthLocalDataSourceImpl(
    private val noteDao: NoteDao,
    private val sharedPreferences: SharedPreferences
) : AuthLocalDataSource {
}