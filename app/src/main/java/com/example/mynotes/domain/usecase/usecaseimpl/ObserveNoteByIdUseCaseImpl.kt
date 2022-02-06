package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.domain.usecase.ObserveNoteByIdUseCase
import kotlinx.coroutines.flow.Flow

class ObserveNoteByIdUseCaseImpl(private val notesDetailDataRepository: NotesDetailDataRepository) :
    ObserveNoteByIdUseCase {
    override fun invoke(noteId: String): Flow<NoteEntity> =
        notesDetailDataRepository.observeNoteById(noteId)
}