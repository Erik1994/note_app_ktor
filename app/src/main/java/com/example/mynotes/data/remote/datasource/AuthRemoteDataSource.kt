package com.example.mynotes.data.remote.datasource

import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.model.response.SimpleResponse
import retrofit2.Response

interface AuthRemoteDataSource {
    suspend fun register(accountRequest: AccountRequest): Response<SimpleResponse>
}