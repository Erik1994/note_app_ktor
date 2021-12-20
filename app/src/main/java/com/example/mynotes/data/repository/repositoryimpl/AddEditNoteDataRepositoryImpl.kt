package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.AddEditNoteLocalDataSource
import com.example.mynotes.data.remote.datasource.AddEditNoteRemoteDataSource
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager

class AddEditNoteDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: AddEditNoteLocalDataSource,
    private val remoteDataSource: AddEditNoteRemoteDataSource
): AddEditNoteDataRepository {
}