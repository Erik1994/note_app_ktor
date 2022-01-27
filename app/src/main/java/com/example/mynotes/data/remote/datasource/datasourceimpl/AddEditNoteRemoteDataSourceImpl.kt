package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.remote.datasource.AddEditNoteRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import okhttp3.ResponseBody
import retrofit2.Response

class AddEditNoteRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : AddEditNoteRemoteDataSource {
    override suspend fun addNote(note: NoteRequest): Response<ResponseBody> =
        apiClients.addNote(note)
}