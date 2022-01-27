package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.usecase.GetNoteByIdUseCase
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow

class GetNoteByIdUseCaseImpl(
    private val addEditNoteDataRepository: AddEditNoteDataRepository
) : GetNoteByIdUseCase {
    override fun invoke(id: String): Flow<SingleLiveEvent<Resource<NoteEntity>>> =
        addEditNoteDataRepository.getNoteById(id)
}