package com.example.mynotes.data.local.datasource.datasourceimpl

import android.content.SharedPreferences
import com.example.mynotes.data.extensions.get
import com.example.mynotes.data.extensions.put
import com.example.mynotes.data.local.EMAIL_KEY
import com.example.mynotes.data.local.TOKEN_KEY
import com.example.mynotes.data.local.datasource.AuthLocalDataSource
import com.example.mynotes.ui.extensions.emptyString

class AuthLocalDataSourceImpl(
    private val sharedPreferences: SharedPreferences
) : AuthLocalDataSource {

    override fun saveToken(token: String) = sharedPreferences.put(TOKEN_KEY, token)
    override fun saveEmail(email: String) = sharedPreferences.put(EMAIL_KEY, email)
    override fun isLoggedIn(): Boolean = sharedPreferences.get(TOKEN_KEY, emptyString()).isNotEmpty()
}