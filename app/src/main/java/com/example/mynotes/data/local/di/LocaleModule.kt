package com.example.mynotes.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.mynotes.data.extensions.PREFS_NAME
import com.example.mynotes.data.local.APP_DATABASE
import com.example.mynotes.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {

    single {
        androidContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

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