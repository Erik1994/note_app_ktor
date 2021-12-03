package com.example.mynotes.di

import com.example.mynotes.data.local.di.localModule
import com.example.mynotes.data.remote.di.remoteModule
import com.example.mynotes.data.repository.di.repositoryModule
import com.example.mynotes.domain.di.domainModule

val diModule = listOf(
    localModule,
    remoteModule,
    repositoryModule,
    domainModule
)