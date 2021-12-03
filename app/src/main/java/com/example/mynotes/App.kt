package com.example.mynotes

import android.app.Application
import com.example.mynotes.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(diModule)
        }
    }
}