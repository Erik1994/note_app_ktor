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
    factory<AddEditNoteDataRepository> { AddEditNoteDataRepositoryImpl(get(), get(), get()) }
    factory<AuthDataRepository> { AuthDataRepositoryImpl(get(), get()) }
    factory<NotesDataRepository> { NotesDataRepositoryImpl(get(), get(), get()) }
    factory<NotesDetailDataRepository> { NotesDetailDataRepositoryImpl(get(), get(), get()) }
}