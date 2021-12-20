package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.Flow

class LoginUseCaseImpl(private val authDataRepository: AuthDataRepository): LoginUseCase {
    override fun invoke(accountRequest: AccountRequest): Flow<Resource<SimpleData>> =
        authDataRepository.login(accountRequest)
}