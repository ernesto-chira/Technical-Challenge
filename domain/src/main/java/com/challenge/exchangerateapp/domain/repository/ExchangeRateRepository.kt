package com.challenge.exchangerateapp.domain.repository

import com.challenge.exchangerateapp.domain.entity.ExchangeRate
import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity

interface ExchangeRateRepository {
    suspend fun getExchangeRate(base: String, symbol: String?): ExchangeRate
    suspend fun insertExchangeRate(data: ExchangeRateEntity)
    suspend fun getAllExchangeRates(callback: (List<ExchangeRateEntity>) -> Unit)
    suspend fun deleteAllExchangeRates()
}