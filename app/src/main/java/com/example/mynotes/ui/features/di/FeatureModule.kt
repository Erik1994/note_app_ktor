package com.example.mynotes.ui.features.di

import com.example.mynotes.ui.features.auth.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel {
        AuthViewModel(appDispatchers = get(), registerUseCase = get())
    }
}