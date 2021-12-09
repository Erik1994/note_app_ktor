package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients

class NotesRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : NotesRemoteDataSource {
}