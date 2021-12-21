package com.example.mynotes.data.local.datasource

interface AuthLocalDataSource {
    fun saveToken(token: String)
    fun isLoggedIn(): Boolean
}