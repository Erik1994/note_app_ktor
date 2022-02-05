package com.example.mynotes.ui.features.notes

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.local.db.mapper.NOTE_ENTITY_TO_REQUEST_MAPPER
import com.example.mynotes.data.local.db.mapper.NOTE_REQUEST_TO_ENTITY_MAPPER
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.*
import com.example.mynotes.ui.common.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotesViewModel(
    private val appDispatchers: AppDispatchers,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : BaseViewModel() {
    private val _forceUpdate = MutableStateFlow(false)

    private val _notesFlow = _forceUpdate.flatMapLatest {
        getNotesUseCase()
    }

    val notesSharedFlow =
        _notesFlow.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)

    fun syncAllNotes() = viewModelScope.launch(appDispatchers.ioDispatcher) {
        _forceUpdate.emit(true)
    }

    fun deleteToken() = deleteTokenUseCase()

    fun deleteNoteById(noteId: String) = viewModelScope.launch {
        deleteNoteUseCase.deleteNoteById(noteId)
    }

    fun addNote(noteEntity: NoteEntity) {
        val noteRequest = NOTE_ENTITY_TO_REQUEST_MAPPER.map(noteEntity)
        addNoteUseCase(noteRequest)
            .onEach {
                when (it) {
                    is Resource.Error -> {
                        insertNoteUseCase(noteEntity)
                    }

                    is Resource.Success -> {
                        insertNoteUseCase(noteEntity.apply { isSynced = true })
                    }

                    is Resource.Loading -> Unit
                }
            }.flowOn(appDispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    fun deleteLocallyDeletedNoteId(noteId: String) = viewModelScope.launch {
        deleteNoteUseCase.deleteLocallyDeletedNoteId(noteId)
    }
}