package com.example.mynotes.domain.di

import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.LoginUseCase
import com.example.mynotes.domain.usecase.RegisterUseCase
import com.example.mynotes.domain.usecase.usecaseimpl.LoginUseCaseImpl
import com.example.mynotes.domain.usecase.usecaseimpl.RegisterUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory { AppDispatchers() }
    factory<RegisterUseCase> { RegisterUseCaseImpl(authDataRepository = get()) }
    factory<LoginUseCase> { LoginUseCaseImpl(authDataRepository = get()) }
}