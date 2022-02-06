package com.example.mynotes.data.remote.datasource

import com.example.mynotes.data.model.request.DeleteNoteRequest
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.model.response.NoteResponse
import okhttp3.ResponseBody
import retrofit2.Response

interface NotesRemoteDataSource {
    suspend fun getAllNotes(): Response<List<NoteResponse>>
    suspend fun deleteNote(
        deleteNoteRequest: DeleteNoteRequest
    ): Response<ResponseBody>
    suspend fun addNote(note: NoteRequest): Response<ResponseBody>
}