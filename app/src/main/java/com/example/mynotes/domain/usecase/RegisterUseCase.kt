package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AccountRequest
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface RegisterUseCase {

    operator fun invoke(accountRequest: AccountRequest): Flow<Resource<SimpleData>>
}