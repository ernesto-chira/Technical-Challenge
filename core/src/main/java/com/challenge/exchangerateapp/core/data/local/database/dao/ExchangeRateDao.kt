package com.challenge.exchangerateapp.core.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.exchangerateapp.core.data.local.database.entities.ExchangeRateDto

@Dao
interface ExchangeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExchangeRate(exchangeRate: ExchangeRateDto): Long

    @Query("SELECT * FROM exchange_rates")
    fun getAllExchangeRates(): List<ExchangeRateDto>

    @Query("DELETE FROM exchange_rates")
    fun deleteAllExchangeRates()
}