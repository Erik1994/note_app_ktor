package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.domain.usecase.InsertNoteUseCase

class InsertNoteUseCaseImpl(private val addEditNoteDataRepository: AddEditNoteDataRepository) :
    InsertNoteUseCase {
    override suspend fun invoke(note: NoteEntity) = addEditNoteDataRepository.insertNote(note)
}