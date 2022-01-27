package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.entity.NoteEntity
import com.example.mynotes.data.repository.util.Resource
import com.example.mynotes.ui.navigation.SingleLiveEvent
import kotlinx.coroutines.flow.Flow

interface GetNoteByIdUseCase {
    operator fun invoke(id: String): Flow<SingleLiveEvent<Resource<NoteEntity>>>
}