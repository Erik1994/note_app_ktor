package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import retrofit2.Response

class NoteDetailRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : NoteDetailRemoteDataSource {
    override suspend fun addOwnersToNote(addOwnerRequest: AddOwnerRequest): Response<SimpleResponse> =
        apiClients.addOwnerToNote(addOwnerRequest)
}