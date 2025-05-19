package com.challenge.exchangerateapp.core.data.local.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRateDto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val currency: String,
    val currencyExchange: String,
    val bidRate: Double,
    val inputAmount: String,
    val outputAmount: String,
    val date: Long
)