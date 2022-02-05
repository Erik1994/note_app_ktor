package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.request.DeleteNoteRequest
import com.example.mynotes.data.model.response.NoteResponse
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import okhttp3.ResponseBody
import retrofit2.Response

class NotesRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : NotesRemoteDataSource {
    override suspend fun getAllNotes(): Response<List<NoteResponse>> = apiClients.getNotes()
    override suspend fun deleteNote(deleteNoteRequest: DeleteNoteRequest): Response<ResponseBody> =
        apiClients.deleteNote(deleteNoteRequest)
}