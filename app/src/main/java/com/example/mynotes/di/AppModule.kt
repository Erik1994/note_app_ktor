package com.example.mynotes.di

import com.example.mynotes.data.repository.util.ConnectionManager
import com.example.mynotes.data.repository.util.EventManager
import com.example.mynotes.domain.dispatchers.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val appModule = module {
    single { ConnectionManager(context = get()) }
    single { EventManager() }
    single { CoroutineScope(SupervisorJob()) }
    factory { AppDispatchers() }
}