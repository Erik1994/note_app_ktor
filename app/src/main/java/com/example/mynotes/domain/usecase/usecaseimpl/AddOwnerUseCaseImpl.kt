package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.usecase.AddOwnerUseCase
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AddOwnerUseCaseImpl(private val notesDetailDataRepository: NotesDetailDataRepository) :
    AddOwnerUseCase {
    override fun invoke(addOwnerRequest: AddOwnerRequest): Flow<SingleLiveEvent<Resource<SimpleData>>> =
        notesDetailDataRepository.addOwnerToNode(addOwnerRequest).map {
            SingleLiveEvent(it)
        }
}