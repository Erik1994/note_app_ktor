package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.domain.usecase.DeleteTokenUseCase

class DeleteTokenUseCaseImpl(private val notesDataRepository: NotesDataRepository): DeleteTokenUseCase {
    override fun invoke() = notesDataRepository.deleteToken()
}