package com.example.testapp

import android.app.Application
import com.example.testapp.data.local.AppDatabase
import com.example.testapp.di.appModule
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppDatabase.getDatabase(this)
        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
        }
    }
}