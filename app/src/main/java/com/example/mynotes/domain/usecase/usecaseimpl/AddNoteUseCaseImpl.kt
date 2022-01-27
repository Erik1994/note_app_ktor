package com.example.mynotes.domain.usecase.usecaseimpl

import com.example.mynotes.data.local.db.mapper.NOTE_REQUEST_TO_ENTITY_MAPPER
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.AddNoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddNoteUseCaseImpl(
    private val coroutineScope: CoroutineScope,
    private val appDispatchers: AppDispatchers,
    private val addEditNoteDataRepository: AddEditNoteDataRepository
) : AddNoteUseCase {
    override fun invoke(note: NoteRequest) {
        val noteEntity = NOTE_REQUEST_TO_ENTITY_MAPPER.map(note)
        addEditNoteDataRepository.addNote(note)
            .catch {
                addEditNoteDataRepository.insertNote(noteEntity)
            }.onEach {
                when (it) {
                    is Resource.Error -> {
                        addEditNoteDataRepository.insertNote(noteEntity)
                    }

                    is Resource.Success -> {
                        addEditNoteDataRepository.insertNote(noteEntity.apply { isSynced = true })
                    }

                    is Resource.Loading -> Unit
                }
            }.flowOn(appDispatchers.ioDispatcher)
            .launchIn(coroutineScope)
    }
}