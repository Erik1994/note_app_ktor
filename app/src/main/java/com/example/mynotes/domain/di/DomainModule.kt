package com.example.mynotes.domain.di

import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.*
import com.example.mynotes.domain.usecase.usecaseimpl.*
import org.koin.dsl.module

val domainModule = module {
    factory { AppDispatchers() }
    factory<RegisterUseCase> { RegisterUseCaseImpl(authDataRepository = get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(authDataRepository = get()) }
    factory<CheckLoginUseCase> { CheckLoginUseCaseImpl(authDataRepository = get()) }
    factory<DeleteTokenUseCase> { DeleteTokenUseCaseImpl(notesDataRepository = get()) }
    factory<GetNotesUseCase> { GetNotesUseCaseImpl(notesDataRepository = get()) }
}