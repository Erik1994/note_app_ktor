package com.example.mynotes.data.remote.datasource

import com.example.mynotes.data.model.response.NoteResponse
import retrofit2.Response

interface NotesRemoteDataSource {
    suspend fun getAllNotes(): Response<List<NoteResponse>>
}