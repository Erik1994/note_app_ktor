package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NotesLocalDataSource
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.repository.NotesDataRepository

class NotesDataRepositoryImpl(
    private val localDataSource: NotesLocalDataSource,
    private val remoteDataSource: NotesRemoteDataSource
) : NotesDataRepository {
}