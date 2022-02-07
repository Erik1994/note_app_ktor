package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.R
import com.example.mynotes.data.emailPassValidation
import com.example.mynotes.data.local.datasource.NoteDetailLocalDataSource
import com.example.mynotes.data.local.db.mapper.SIMPLE_RESPONSE_TO_DATA_MAPPER
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotesDetailDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: NoteDetailLocalDataSource,
    private val remoteDataSource: NoteDetailRemoteDataSource
) : NotesDetailDataRepository {
    override fun observeNoteById(noteId: String): Flow<NoteEntity> =
        localDataSource.observeNoteById(noteId)

    override fun addOwnerToNode(addOwnerRequest: AddOwnerRequest): Flow<Resource<SimpleData>> = flow {
        val context = connectionManager.context
        emit(Resource.Loading(null))
        if(addOwnerRequest.ownerEmail.isEmpty() || addOwnerRequest.noteId.isEmpty()) {
            emit(Resource.Error(context.getString(R.string.owner_empty_error_message), null))
        } else {
            try {
                val response = remoteDataSource.addOwnersToNote(addOwnerRequest)
                if (response.isSuccessful && response.body()?.successful == true) {
                    response.body()?.let {
                        emit(Resource.Success(SIMPLE_RESPONSE_TO_DATA_MAPPER.map(it)))
                    }
                } else {
                    emit(
                        Resource.Error(
                            response.body()?.message ?: response.message(),
                            null,
                            response.code()
                        )
                    )
                }
            } catch (e: Exception) {
                emit(Resource.Error(connectionManager.getErrorMessage(context, e), null))
            }
        }
    }
}