package com.example.mynotes.data.local.datasource

interface AuthLocalDataSource {
    fun saveToken(token: String)
    fun saveEmail(email: String)
    fun isLoggedIn(): Boolean
}