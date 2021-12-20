package com.example.mynotes.data.repository.di

import com.example.mynotes.data.repository.AddEditNoteDataRepository
import com.example.mynotes.data.repository.AuthDataRepository
import com.example.mynotes.data.repository.NotesDataRepository
import com.example.mynotes.data.repository.NotesDetailDataRepository
import com.example.mynotes.data.repository.repositoryimpl.AddEditNoteDataRepositoryImpl
import com.example.mynotes.data.repository.repositoryimpl.AuthDataRepositoryImpl
import com.example.mynotes.data.repository.repositoryimpl.NotesDataRepositoryImpl
import com.example.mynotes.data.repository.repositoryimpl.NotesDetailDataRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<AddEditNoteDataRepository> {
        AddEditNoteDataRepositoryImpl(
            connectionManager = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<AuthDataRepository> {
        AuthDataRepositoryImpl(
            connectionManager = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<NotesDataRepository> {
        NotesDataRepositoryImpl(
            connectionManager = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
    factory<NotesDetailDataRepository> {
        NotesDetailDataRepositoryImpl(
            connectionManager = get(),
            localDataSource = get(),
            remoteDataSource = get()
        )
    }
}