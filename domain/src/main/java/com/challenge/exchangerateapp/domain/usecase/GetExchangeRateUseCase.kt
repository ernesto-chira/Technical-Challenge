package com.challenge.exchangerateapp.domain.usecase

import com.challenge.exchangerateapp.domain.entity.ExchangeRate
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository

class GetExchangeRateUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend fun execute(base: String, symbol: String? = null): ExchangeRate {
        return repository.getExchangeRate(base, symbol)
    }
}