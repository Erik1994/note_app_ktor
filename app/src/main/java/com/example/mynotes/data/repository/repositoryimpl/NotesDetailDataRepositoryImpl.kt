package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import kotlinx.coroutines.flow.Flow

class NotesDetailDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: NoteDetailLocalDataSource,
    private val remoteDataSource: NoteDetailRemoteDataSource
) : NotesDetailDataRepository {
    override fun observeNoteById(noteId: String): Flow<NoteEntity> =
        localDataSource.observeNoteById(noteId)
}