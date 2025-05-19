package com.challenge.exchangerateapp.core.data.repository

import com.challenge.exchangerateapp.core.BuildConfig
import com.challenge.exchangerateapp.core.data.local.database.dao.ExchangeRateDao
import com.challenge.exchangerateapp.core.data.mapper.toExchangeRateDto
import com.challenge.exchangerateapp.core.data.mapper.toExchangeRateEntity
import com.challenge.exchangerateapp.core.network.ExchangeRateService
import com.challenge.exchangerateapp.domain.entity.ExchangeRate
import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository
import java.util.concurrent.Executors

class ExchangeRateRepositoryImpl(
    private val exchangeRateService: ExchangeRateService,
    private val dao: ExchangeRateDao
) : ExchangeRateRepository {

    private val apiKey = BuildConfig.API_KEY
    private val executor = Executors.newSingleThreadExecutor()

    override suspend fun getExchangeRate(base: String, symbol: String?): ExchangeRate {
        val response = exchangeRateService.getExchangeRate(base, apiKey)
        if (response.error == null) {
            return ExchangeRate(base, symbol.orEmpty(), response.rates)
        } else {
            // error("Exchange rate not found for $symbol")
            println("error")
            return ExchangeRate("", "", mapOf())
        }
    }

    override suspend fun insertExchangeRate(data: ExchangeRateEntity) {
        executor.execute {
            dao.insertExchangeRate(data.toExchangeRateDto())
        }
    }

    override suspend fun getAllExchangeRates(callback: (List<ExchangeRateEntity>) -> Unit) {
        executor.execute {
            try {
                val exchangeRates = dao.getAllExchangeRates()
                callback(
                    exchangeRates.map { it.toExchangeRateEntity() }
                )
            } catch (e: Exception) {
                println("Error fetching exchange rates: ${e.message}")
                callback(emptyList())
            }
        }
    }

    override suspend fun deleteAllExchangeRates() {
        executor.execute {
            dao.deleteAllExchangeRates()
        }
    }
}