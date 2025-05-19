package com.challenge.exchangerateapp.core.di

import android.app.Application
import androidx.room.Room
import com.challenge.exchangerateapp.core.data.local.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            get<Application>(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().exchangeRecordDao() }
}