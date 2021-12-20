package com.example.mynotes.domain.di

import com.example.mynotes.domain.dispatchers.AppDispatchers
import com.example.mynotes.domain.usecase.RegisterUseCase
import com.example.mynotes.domain.usecase.RegisterUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory { AppDispatchers() }
    factory<RegisterUseCase> { RegisterUseCaseImpl(get()) }
}