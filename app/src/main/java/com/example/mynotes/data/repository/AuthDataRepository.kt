package com.example.mynotes.data.repository

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthDataRepository {
    fun register(accountRequest: AccountRequest): Flow<Resource<SimpleData>>
    fun login(accountRequest: AccountRequest): Flow<Resource<SimpleData>>
    fun isLoggedIn(): Boolean
}