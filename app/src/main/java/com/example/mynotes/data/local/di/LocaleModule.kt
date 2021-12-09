package com.example.mynotes.data.local.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.mynotes.data.local.APP_DATABASE
import com.example.mynotes.data.local.PREFS_NAME
import com.example.mynotes.data.local.datasource.datasourceimpl.AddEditNoteLocalDataSourceImpl
import com.example.mynotes.data.local.datasource.datasourceimpl.AuthLocalDataSourceImpl
import com.example.mynotes.data.local.datasource.datasourceimpl.NoteDetailLocalDataSourceImpl
import com.example.mynotes.data.local.datasource.datasourceimpl.NotesLocalDataSourceImpl
import com.example.mynotes.data.local.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import kotlin.math.sin

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

    fun provideEncryptedSharedPref(
        context: Context
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun provideNoteDao(database: AppDatabase) = database.noteDao()
    single { provideNoteDao(get()) }
    single { AddEditNoteLocalDataSourceImpl(get(), get()) }
    single { NoteDetailLocalDataSourceImpl(get(), get()) }
    single { AuthLocalDataSourceImpl(get(), get()) }
    single { NotesLocalDataSourceImpl(get(), get()) }
}