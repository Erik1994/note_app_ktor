package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.usecase.GetNotesUseCase
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNotesUseCaseImpl(
    private val notesDataRepository: NotesDataRepository
): GetNotesUseCase {
    override fun invoke(): Flow<SingleLiveEvent<Resource<List<NoteEntity>>>> = notesDataRepository.getAllNotes().map{
        SingleLiveEvent(it)
    }
}