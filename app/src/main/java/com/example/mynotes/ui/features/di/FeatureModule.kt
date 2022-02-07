package com.example.mynotes.ui.features.di

import com.example.mynotes.ui.features.addeditnote.AddEditNoteViewModel
import com.example.mynotes.ui.features.auth.AuthViewModel
import com.example.mynotes.ui.features.notedetail.NoteDetailViewModel
import com.example.mynotes.ui.features.notes.NotesViewModel
import com.example.mynotes.ui.features.notes.adapter.NotesAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    factory {
        NotesAdapter(
            coroutineScope = get(),
            appDispatchers = get()
        )
    }

    viewModel {
        AuthViewModel(
            appDispatchers = get(),
            registerUseCase = get(),
            loginUseCase = get(),
            checkLoginUseCase = get()
        )
    }

    viewModel {
        NotesViewModel(
            deleteTokenUseCase = get(),
            appDispatchers = get(),
            getNotesUseCase = get(),
            deleteNoteUseCase = get(),
            insertNoteUseCase = get(),
            addNoteUseCase = get()
        )
    }

    viewModel {
        NoteDetailViewModel(
            observeNoteByIdUseCase = get(),
            addOwnerUseCase = get(),
            appDispatchers = get()
        )
    }

    viewModel {
        AddEditNoteViewModel(
            appDispatchers = get(),
            addNoteUseCase = get(),
            getNoteByIdUseCase = get(),
            getEmailUseCase = get(),
            insertNoteUseCase = get()
        )
    }
}