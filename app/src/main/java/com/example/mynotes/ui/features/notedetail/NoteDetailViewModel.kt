package com.example.mynotes.ui.features.notedetail

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.model.data.SimpleData
import com.example.mynotes.data.model.request.AddOwnerRequest
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.AddOwnerUseCase
import com.example.mynotes.domain.usecase.ObserveNoteByIdUseCase
import com.example.mynotes.ui.common.BaseViewModel
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val observeNoteByIdUseCase: ObserveNoteByIdUseCase,
    private val addOwnerUseCase: AddOwnerUseCase,
    private val appDispatchers: AppDispatchers
) : BaseViewModel() {

    private val _emailStateFlow = MutableStateFlow<String?>(null)
    val emailStateFlow: StateFlow<String?> = _emailStateFlow

    private val _addOwnerSharedFlow = MutableSharedFlow<SingleLiveEvent<Resource<SimpleData>>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    val addOwnerSharedFlow: SharedFlow<SingleLiveEvent<Resource<SimpleData>>> = _addOwnerSharedFlow.asSharedFlow()

    fun addOwnerToNote(addOwnerRequest: AddOwnerRequest) =
        viewModelScope.launch(appDispatchers.ioDispatcher) {
            _addOwnerSharedFlow.emitAll(addOwnerUseCase(addOwnerRequest))
        }

    fun setEmail(email: String) = viewModelScope.launch {
        _emailStateFlow.emit(email)
    }

    fun observeNoteById(noteId: String) = observeNoteByIdUseCase(noteId)

}