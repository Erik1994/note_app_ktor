package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NotesLocalDataSource
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager

class NotesDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: NotesLocalDataSource,
    private val remoteDataSource: NotesRemoteDataSource
) : NotesDataRepository {
    override fun deleteToken() = localDataSource.deleteToken()
}