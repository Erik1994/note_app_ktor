package com.example.mynotes.ui.features.notes

import androidx.lifecycle.viewModelScope
import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.DeleteTokenUseCase
import com.example.mynotes.domain.usecase.GetNotesUseCase
import com.example.mynotes.ui.common.BaseViewModel
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotesViewModel(
    private val appDispatchers: AppDispatchers,
    private val deleteTokenUseCase: DeleteTokenUseCase,
    private val getNotesUseCase: GetNotesUseCase
) : BaseViewModel() {
//    private val _notesSharedFlow = MutableSharedFlow<SingleLiveEvent<Resource<List<NoteEntity>>>>(
//        replay = 1,
//        onBufferOverflow = BufferOverflow.DROP_OLDEST
//    )
    private val _forceUpdate = MutableStateFlow(false)
    fun syncAllNotes() = viewModelScope.launch(appDispatchers.ioDispatcher) {
            _forceUpdate.emit(true)
        }
    private val _notesSharedFlow = _forceUpdate.flatMapLatest {
        getNotesUseCase()
    }

    val notesSharedFlow = _notesSharedFlow.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)

    fun deleteToken() = deleteTokenUseCase()
}