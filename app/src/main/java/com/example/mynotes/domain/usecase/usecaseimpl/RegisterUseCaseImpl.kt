package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.Flow

class RegisterUseCaseImpl(private val authDataRepository: AuthDataRepository): RegisterUseCase {
    override operator fun invoke(accountRequest: AccountRequest): Flow<Resource<SimpleData>> =
        authDataRepository.register(accountRequest)
}