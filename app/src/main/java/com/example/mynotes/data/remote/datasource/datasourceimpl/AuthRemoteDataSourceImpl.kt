package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients

class AuthRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : AuthRemoteDataSource {
}