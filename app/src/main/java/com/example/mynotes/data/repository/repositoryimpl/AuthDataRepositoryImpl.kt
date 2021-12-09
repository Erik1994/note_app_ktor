package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.local.datasource.AuthLocalDataSource
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.repository.AuthDataRepository

class AuthDataRepositoryImpl(
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource
) : AuthDataRepository {
}