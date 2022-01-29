package com.example.mynotes.ui.features.addeditnote

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.local.db.mapper.NOTE_REQUEST_TO_ENTITY_MAPPER
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.AddNoteUseCase
import com.example.mynotes.domain.usecase.GetEmailUseCase
import com.example.mynotes.domain.usecase.GetNoteByIdUseCase
import com.example.mynotes.domain.usecase.InsertNoteUseCase
import com.example.mynotes.ui.common.BaseViewModel
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddEditNoteViewModel(
    private val appDispatchers: AppDispatchers,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val getEmailUseCase: GetEmailUseCase,
    private val insertNoteUseCase: InsertNoteUseCase
) : BaseViewModel() {
    private val _noteSharedFlow = MutableSharedFlow<SingleLiveEvent<Resource<NoteEntity>>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val noteSharedFlow = _noteSharedFlow.asSharedFlow()
    private val _colorSharedFlow = MutableStateFlow<String?>(null)
    val colorSharedFlow = _colorSharedFlow.asStateFlow()
    private val _navigateNotesFragmentSharedFlow = MutableSharedFlow<Boolean>()
    val navigateNotesFragmentSharedFlow = _navigateNotesFragmentSharedFlow.asSharedFlow()

    fun addNote(note: NoteRequest) {
        val noteEntity = NOTE_REQUEST_TO_ENTITY_MAPPER.map(note)
        addNoteUseCase(note)
            .onEach {
                when (it) {
                    is Resource.Error -> {
                        insertNoteUseCase(noteEntity)
                        _navigateNotesFragmentSharedFlow.emit(true)
                    }

                    is Resource.Success -> {
                        insertNoteUseCase(noteEntity.apply { isSynced = true })
                        _navigateNotesFragmentSharedFlow.emit(true)
                    }

                    is Resource.Loading -> Unit
                }
            }.flowOn(appDispatchers.ioDispatcher)
            .launchIn(viewModelScope)
    }

    fun getNoteById(id: String) = viewModelScope.launch(appDispatchers.ioDispatcher) {
        _noteSharedFlow.emitAll(getNoteByIdUseCase(id))
    }

    fun getEmail() = getEmailUseCase()

    fun setColor(color: String) = viewModelScope.launch {
        _colorSharedFlow.emit(color)
    }
}