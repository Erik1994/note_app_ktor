package com.example.mynotes.data.repository.repositoryimpl

import com.example.mynotes.data.emailPassValidation
import com.example.mynotes.data.local.db.mapper.SIMPLE_RESPONSE_TO_DATA_MAPPER
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import safeApiCall

class AuthDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val remoteDataSource: AuthRemoteDataSource
) : AuthDataRepository {
    override fun register(accountRequest: AccountRequest): Flow<Resource<SimpleData>> {
        val validationMessage = emailPassValidation(
            connectionManager.context,
            accountRequest.email,
            accountRequest.password,
            accountRequest.repeatedPassword
        )
        return if (validationMessage.isNotEmpty()) {
            flow {
                emit(Resource.Error(validationMessage, null))
            }
        } else {
            safeApiCall(
                mapper = SIMPLE_RESPONSE_TO_DATA_MAPPER,
                connectionManager = connectionManager,
                apiCall = { remoteDataSource.register(accountRequest) }
            )
        }
    }

}