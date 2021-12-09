package com.example.mynotes.data.remote.network

import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.model.request.DeleteNoteRequest
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.model.response.NoteResponse
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.data.model.response.TokenResponse
import com.example.mynotes.data.remote.*
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClients {

    @POST(REGISTER)
    suspend fun register(
        @Body registerRequest: AccountRequest
    ): Response<SimpleResponse>

    @POST(LOGIN)
    suspend fun login(
        @Body loginRequest: AccountRequest
    ): Response<TokenResponse>

    @POST(ADD_NOTE)
    suspend fun addNote(
        @Body note: NoteRequest
    ): Response<ResponseBody>

    @POST(DELETE_NOTE)
    suspend fun deleteNote(
        @Body deleteNoteRequest: DeleteNoteRequest
    ): Response<ResponseBody>

    @POST(ADD_OWNER_TO_NOTE)
    suspend fun addOwnerToNote(
        @Body addOwnerRequest: AddOwnerRequest
    ): Response<SimpleResponse>

    @GET(GET_NOTES)
    suspend fun getNotes(): Response<List<NoteResponse>>

}