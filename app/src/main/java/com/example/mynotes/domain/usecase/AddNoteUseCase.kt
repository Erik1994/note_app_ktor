package com.example.mynotes.domain.usecase

import com.example.mynotes.data.model.request.NoteRequest
import com.example.mynotes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody

interface AddNoteUseCase {
    operator fun invoke(note: NoteRequest): Flow<Resource<ResponseBody>>
}