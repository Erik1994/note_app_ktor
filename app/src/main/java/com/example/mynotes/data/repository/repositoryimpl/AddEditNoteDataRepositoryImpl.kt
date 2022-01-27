package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.R
import com.example.mynotes.data.local.datasource.AddEditNoteLocalDataSource
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.remote.datasource.AddEditNoteRemoteDataSource
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.ResponseBody
import safeApiCall

class AddEditNoteDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val localDataSource: AddEditNoteLocalDataSource,
    private val remoteDataSource: AddEditNoteRemoteDataSource
) : AddEditNoteDataRepository {
    override suspend fun insertNote(note: NoteEntity) = localDataSource.insertNote(note)

    override fun getEmail(): String = localDataSource.getEmail()

    override fun addNote(note: NoteRequest): Flow<Resource<ResponseBody>> =
        safeApiCall(
            connectionManager = connectionManager,
            apiCall = { remoteDataSource.addNote(note) }
        )

    override fun getNoteById(id: String): Flow<SingleLiveEvent<Resource<NoteEntity>>> = flow {
        emit(SingleLiveEvent(Resource.Loading(null)))
        val note = localDataSource.getNoteById(id)
        note?.let {
            emit(SingleLiveEvent(Resource.Success(it)))
        } ?: emit(
            SingleLiveEvent(
                Resource.Error(
                    connectionManager.context.getString(R.string.note_not_found),
                    null
                )
            )
        )
    }

}