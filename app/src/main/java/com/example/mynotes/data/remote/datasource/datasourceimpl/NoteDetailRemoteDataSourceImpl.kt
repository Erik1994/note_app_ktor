package com.example.mynotes.data.remote.datasource.datasourceimpl

import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.remote.network.ApiClients

class NoteDetailRemoteDataSourceImpl(
    private val apiClients: ApiClients
) : NoteDetailRemoteDataSource {
}