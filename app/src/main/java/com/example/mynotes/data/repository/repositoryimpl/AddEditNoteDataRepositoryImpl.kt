package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.AddEditNoteLocalDataSource
import com.example.mynotes.data.remote.datasource.AddEditNoteRemoteDataSource

class AddEditNoteDataRepositoryImpl(
    private val localDataSource: AddEditNoteLocalDataSource,
    private val remoteDataSource: AddEditNoteRemoteDataSource
) {
}