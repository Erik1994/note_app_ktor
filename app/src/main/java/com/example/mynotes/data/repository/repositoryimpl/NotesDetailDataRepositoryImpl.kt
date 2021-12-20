package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager

class NotesDetailDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: NoteDetailLocalDataSource,
    private val remoteDataSource: NoteDetailRemoteDataSource
) : NotesDetailDataRepository {
}