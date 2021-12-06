package com.example.mynotes.data.local.di

import androidx.room.Room
import com.example.mynotes.data.local.APP_DATABASE
import com.example.mynotes.data.local.db.AppDatabase
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            APP_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideNoteDao(database: AppDatabase) = database.noteDao()
    single { provideNoteDao(get()) }
}