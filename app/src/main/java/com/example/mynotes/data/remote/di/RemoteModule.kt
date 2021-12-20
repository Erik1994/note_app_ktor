package com.example.mynotes.data.remote.di

import com.example.mynotes.data.remote.datasource.AddEditNoteRemoteDataSource
import com.example.mynotes.data.remote.datasource.AuthRemoteDataSource
import com.example.mynotes.data.remote.datasource.NoteDetailRemoteDataSource
import com.example.mynotes.data.remote.datasource.NotesRemoteDataSource
import com.example.mynotes.data.remote.datasource.datasourceimpl.AddEditNoteRemoteDataSourceImpl
import com.example.mynotes.data.remote.datasource.datasourceimpl.AuthRemoteDataSourceImpl
import com.example.mynotes.data.remote.datasource.datasourceimpl.NoteDetailRemoteDataSourceImpl
import com.example.mynotes.data.remote.datasource.datasourceimpl.NotesRemoteDataSourceImpl
import com.example.mynotes.data.remote.network.ApiClients
import com.example.mynotes.data.remote.network.interceptor.AuthTokenInterceptor
import com.example.mynotes.data.remote.network.interceptor.ContentTypeInterceptor
import com.example.mynotes.data.repository.util.ConnectionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun remoteModule(baseUrl: String) = module {

    factory<Interceptor> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        }
    }

    single {
        return@single AuthTokenInterceptor(get())
    }

//    single {
//        return@single HeaderInterceptor(get())
//    }

    single {
        return@single ContentTypeInterceptor()
    }

    factory {
        OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<ContentTypeInterceptor>())
            .addInterceptor(get<AuthTokenInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(ApiClients::class.java)
    }

    single { ConnectionManager(context = get()) }
    single<AddEditNoteRemoteDataSource> { AddEditNoteRemoteDataSourceImpl(apiClients = get()) }
    single<NoteDetailRemoteDataSource> { NoteDetailRemoteDataSourceImpl(apiClients = get()) }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(apiClients = get()) }
    single<NotesRemoteDataSource> { NotesRemoteDataSourceImpl(apiClients = get()) }
}