package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.NotesLocalDataSource
import com.example.mynotes.data.local.db.mapper.NOTES_RESPONSE_TO_ENTITY_MAPPER
import com.example.mynotes.data.model.entity.LocallyDeletedNoteId
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.DeleteNoteRequest
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import safeCashedApiCall

class NotesDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: NotesLocalDataSource,
    private val remoteDataSource: NotesRemoteDataSource
) : NotesDataRepository {
    override fun deleteToken() = localDataSource.deleteToken()
    override fun getAllNotes(): Flow<Resource<List<NoteEntity>>> =
        safeCashedApiCall(
            mapper = NOTES_RESPONSE_TO_ENTITY_MAPPER,
            connectionManager = connectionManager,
            dbQuery = { localDataSource.getAllNotes() },
            apiCall = { remoteDataSource.getAllNotes() },
            dbSaver = { noteEntityList ->
                insertNotes(noteEntityList.onEach {
                    it.isSynced = true
                })
            }
        )

    override suspend fun deleteNoteById(noteId: String) {
        val response = try {
            remoteDataSource.deleteNote(DeleteNoteRequest(noteId))
        } catch (e: Exception) {
            null
        }
        localDataSource.deleteNoteByID(noteId)
        if (response == null || !response.isSuccessful) {
            insertDeletedNoteId(LocallyDeletedNoteId(noteId))
        } else {
            deleteLocallyDeletedNoteId(noteId)
        }
    }

    override suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String) =
        localDataSource.deleteLocallyDeletedNoteId(deletedNoteId)

    override suspend fun getAllLocallyDeletedNoteIds(): List<LocallyDeletedNoteId> =
        localDataSource.getAllLocallyDeletedNoteIds()

    override suspend fun getAllUnSyncedNotes(): List<NoteEntity> =
        localDataSource.getAllUnSyncedNotes()

    override suspend fun insertDeletedNoteId(locallyDeletedNoteId: LocallyDeletedNoteId) =
        localDataSource.insertDeletedNoteId(locallyDeletedNoteId)

    override suspend fun insertNotes(notes: List<NoteEntity>) = localDataSource.insertNotes(notes)
}