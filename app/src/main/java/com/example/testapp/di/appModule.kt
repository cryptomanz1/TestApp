package com.example.testapp.di

import androidx.room.Room
import com.example.testapp.data.local.AppDatabase
import com.example.testapp.data.local.EntryDao
import com.example.testapp.data.local.EntryRepositoryImpl
import com.example.testapp.domain.repository.EntryRepository
import com.example.testapp.presentation.viewmodel.EntryViewModel
import org.koin.core.module.dsl.viewModelOf

import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single<EntryDao> { get<AppDatabase>().EntryDao() }
    single<EntryRepository> { EntryRepositoryImpl(get()) }
    viewModelOf(::EntryViewModel)
}