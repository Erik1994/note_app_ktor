package com.example.mynotes.di

import com.example.mynotes.data.local.di.localModule
import com.example.mynotes.data.remote.BASE_URL
import com.example.mynotes.data.remote.di.remoteModule
import com.example.mynotes.data.repository.di.repositoryModule
import com.example.mynotes.domain.di.domainModule
import com.example.mynotes.ui.features.di.featureModule

val diModule = listOf(
    localModule,
    remoteModule(baseUrl = BASE_URL),
    repositoryModule,
    domainModule,
    featureModule
)