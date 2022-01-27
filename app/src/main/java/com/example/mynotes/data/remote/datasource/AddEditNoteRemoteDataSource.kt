package com.example.mynotes.data.remote.datasource

import com.example.mynotes.data.model.request.NoteRequest
import okhttp3.ResponseBody
import retrofit2.Response

interface AddEditNoteRemoteDataSource {
    suspend fun addNote(note: NoteRequest): Response<ResponseBody>
}