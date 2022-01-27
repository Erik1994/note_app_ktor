package com.example.mynotes.ui.features.addeditnote

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.AddNoteUseCase
import com.example.mynotes.domain.usecase.GetEmailUseCase
import com.example.mynotes.domain.usecase.GetNoteByIdUseCase
import com.example.mynotes.ui.common.BaseViewModel
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AddEditNoteViewModel(
    private val appDispatchers: AppDispatchers,
    private val addNoteUseCase: AddNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val getEmailUseCase: GetEmailUseCase
) : BaseViewModel() {
    private val _noteSharedFlow = MutableSharedFlow<SingleLiveEvent<Resource<NoteEntity>>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val noteSharedFlow = _noteSharedFlow.asSharedFlow()
    private val _colorSharedFlow = MutableStateFlow<String?>(null)
    val colorSharedFlow = _colorSharedFlow.asStateFlow()

    fun addNote(note: NoteRequest) = viewModelScope.launch(appDispatchers.ioDispatcher) {
        addNoteUseCase(note)
    }

    fun getNoteById(id: String) = viewModelScope.launch(appDispatchers.ioDispatcher) {
        _noteSharedFlow.emitAll(getNoteByIdUseCase(id))
    }

    fun getEmail() = getEmailUseCase()

    fun setColor(color: String) = viewModelScope.launch {
        _colorSharedFlow.emit(color)
    }
}