package com.example.mynotes.ui.features.notes

import androidx.lifecycle.viewModelScope
import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.DeleteTokenUseCase
import com.example.mynotes.domain.usecase.GetNotesUseCase
import com.example.mynotes.ui.common.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.shareIn
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

    private val _notesFlow = _forceUpdate.flatMapLatest {
        getNotesUseCase()
    }

    val notesSharedFlow = _notesFlow.shareIn(viewModelScope, started = SharingStarted.Lazily, replay = 1)

    fun deleteToken() = deleteTokenUseCase()
}