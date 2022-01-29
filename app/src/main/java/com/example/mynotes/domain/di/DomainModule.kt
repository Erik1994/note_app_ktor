package com.example.mynotes.domain.di

import com.example.mynotes.domain.usecase.*
import com.example.mynotes.domain.usecase.usecaseimpl.*
import org.koin.dsl.module

val domainModule = module {
    factory<RegisterUseCase> { RegisterUseCaseImpl(authDataRepository = get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(authDataRepository = get()) }
    factory<CheckLoginUseCase> { CheckLoginUseCaseImpl(authDataRepository = get()) }
    factory<DeleteTokenUseCase> { DeleteTokenUseCaseImpl(notesDataRepository = get()) }
    factory<GetNotesUseCase> { GetNotesUseCaseImpl(notesDataRepository = get()) }
    factory<AddNoteUseCase> { AddNoteUseCaseImpl(addEditNoteDataRepository = get()) }
    factory<GetNoteByIdUseCase> { GetNoteByIdUseCaseImpl(addEditNoteDataRepository = get()) }
    factory<GetEmailUseCase> { GetEmailUseCaseImpl(addEditNoteDataRepository = get()) }
    factory<InsertNoteUseCase> { InsertNoteUseCaseImpl(addEditNoteDataRepository = get()) }
}