package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.domain.usecase.AddNoteUseCase

class AddNoteUseCaseImpl(
    private val addEditNoteDataRepository: AddEditNoteDataRepository
) : AddNoteUseCase {
    override fun invoke(note: NoteRequest) = addEditNoteDataRepository.addNote(note)
}