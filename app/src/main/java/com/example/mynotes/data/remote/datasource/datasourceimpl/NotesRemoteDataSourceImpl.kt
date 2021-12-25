package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.response.NoteResponse
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import retrofit2.Response

class NotesRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : NotesRemoteDataSource {
    override suspend fun getAllNotes(): Response<List<NoteResponse>> = apiClients.getNotes()
}