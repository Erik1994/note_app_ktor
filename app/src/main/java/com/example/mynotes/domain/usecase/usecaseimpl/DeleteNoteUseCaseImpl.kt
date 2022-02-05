package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.domain.usecase.DeleteNoteUseCase

class DeleteNoteUseCaseImpl(
    private val notesDataRepository: NotesDataRepository
) : DeleteNoteUseCase {
    override suspend fun deleteNoteById(noteId: String) = notesDataRepository.deleteNoteById(noteId)

    override suspend fun deleteLocallyDeletedNoteId(deletedNoteId: String) =
        notesDataRepository.deleteLocallyDeletedNoteId(deletedNoteId)
}