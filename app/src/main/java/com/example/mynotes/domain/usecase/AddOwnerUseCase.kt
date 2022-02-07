package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow

interface AddOwnerUseCase {
    operator fun invoke(addOwnerRequest: AddOwnerRequest): Flow<SingleLiveEvent<Resource<SimpleData>>>
}