package com.example.mynotes.data.repository.repositoryimpl

import android.content.Context
import android.util.Log
import com.example.mynotes.R
import com.example.mynotes.data.emailPassValidation
import com.example.mynotes.data.local.datasource.AuthLocalDataSource
import com.example.mynotes.data.local.db.mapper.SIMPLE_RESPONSE_TO_DATA_MAPPER
import com.example.mynotes.data.local.db.mapper.TOKEN_RESPONSE_TO_DATA_MAPPER
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthDataRepositoryImpl(
    private val connectionManager: ConnectionManager,
    private val remoteDataSource: AuthRemoteDataSource,
    private val localDataSource: AuthLocalDataSource
) : AuthDataRepository {
    override fun register(accountRequest: AccountRequest): Flow<Resource<SimpleData>> = flow {
        val context = connectionManager.context
        val validationMessage = emailPassValidation(
            context,
            accountRequest.email,
            accountRequest.password,
            accountRequest.repeatedPassword
        )
        if (validationMessage.isNotEmpty()) {
            emit(Resource.Error(validationMessage, null))
        } else {
            emit(Resource.Loading(null))
            try {
                val response = remoteDataSource.register(accountRequest)
                if (response.isSuccessful && response.body()?.successful == true) {
                    response.body()?.let {
                        emit(Resource.Success(SIMPLE_RESPONSE_TO_DATA_MAPPER.map(it)))
                    }
                } else {
                    emit(
                        Resource.Error(
                            response.body()?.message ?: response.message(),
                            null,
                            response.code()
                        )
                    )
                }
            } catch (e: Exception) {
                emit(Resource.Error(getErrorMessage(context, e), null))
            }
        }
    }

    override fun login(accountRequest: AccountRequest): Flow<Resource<SimpleData>> = flow {
        val context = connectionManager.context
        val validationMessage = emailPassValidation(
            context = context,
            email = accountRequest.email,
            password = accountRequest.password,
            isRegistr = false
        )
        if (validationMessage.isNotEmpty()) {
            emit(Resource.Error(validationMessage, null))
        } else {
            emit(Resource.Loading(null))
            try {
                val response = remoteDataSource.login(accountRequest)
                if (response.isSuccessful && response.body()?.successful == true) {
                    response.body()?.let {
                        it.token?.takeIf { token -> token.isNotEmpty() }?.let { token ->
                            Log.d("TOKENTAG", "Token: $token")
                            localDataSource.saveToken(token)
                        }
                        localDataSource.saveEmail(accountRequest.email)
                        emit(Resource.Success(TOKEN_RESPONSE_TO_DATA_MAPPER.map(it)))
                    }
                } else {
                    emit(
                        Resource.Error(
                            response.body()?.message ?: response.message(),
                            null,
                            response.code()
                        )
                    )
                }
            } catch (e: Exception) {
                emit(Resource.Error(getErrorMessage(context, e), null))
            }
        }
    }

    override fun isLoggedIn(): Boolean = localDataSource.isLoggedIn()

    private fun getErrorMessage(context: Context, e: Exception) =
        if (connectionManager.checkNetworkConnection()) {
            e.message
        } else {
            context.getString(R.string.internet_connection_error)
        }
}