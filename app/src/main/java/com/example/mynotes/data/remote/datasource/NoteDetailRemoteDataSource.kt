package com.example.mynotes.data.remote.datasource

import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.model.response.SimpleResponse
import retrofit2.Response

interface NoteDetailRemoteDataSource {
    suspend fun addOwnersToNote(addOwnerRequest: AddOwnerRequest): Response<SimpleResponse>
}