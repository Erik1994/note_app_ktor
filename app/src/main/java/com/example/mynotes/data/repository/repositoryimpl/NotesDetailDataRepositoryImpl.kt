package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.repository.NotesDetailDataRepository

class NotesDetailDataRepositoryImpl(
    private val localDataSource: NoteDetailLocalDataSource,
    private val remoteDataSource: NoteDetailRemoteDataSource
) : NotesDetailDataRepository {
}