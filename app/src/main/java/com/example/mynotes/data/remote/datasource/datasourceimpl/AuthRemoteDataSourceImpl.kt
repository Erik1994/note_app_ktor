package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.model.response.SimpleResponse
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients
import com.example.mynotes.data.repository.util.ConnectionManager
import retrofit2.Response

class AuthRemoteDataSourceImpl(
    private val apiClients: ApiClients,
    private val connectionManager: ConnectionManager
) : AuthRemoteDataSource {

    override suspend fun register(accountRequest: AccountRequest): Response<SimpleResponse> =
        apiClients.register(
            accountRequest
        )
}