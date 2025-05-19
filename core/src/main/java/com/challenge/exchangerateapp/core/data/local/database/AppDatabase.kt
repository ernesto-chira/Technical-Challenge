package com.challenge.exchangerateapp.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.exchangerateapp.core.data.local.database.dao.ExchangeRateDao
import com.challenge.exchangerateapp.core.data.local.database.entities.ExchangeRateDto

@Database(entities = [ExchangeRateDto::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exchangeRecordDao(): ExchangeRateDao
}