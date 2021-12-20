package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.data.model.response.TokenResponse
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : AuthRemoteDataSource {

    override suspend fun register(accountRequest: AccountRequest): Response<SimpleResponse> =
        apiClients.register(accountRequest)

    override suspend fun login(accountRequest: AccountRequest): Response<TokenResponse> =
        apiClients.login(accountRequest)
}