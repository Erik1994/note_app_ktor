package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.domain.usecase.GetEmailUseCase

class GetEmailUseCaseImpl(private val addEditNoteDataRepository: AddEditNoteDataRepository) :
    GetEmailUseCase {
    override fun invoke(): String = addEditNoteDataRepository.getEmail()
}