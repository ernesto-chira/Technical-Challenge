package com.challenge.exchangerateapp.domain.usecase

import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository

class DeleteExchangeRatesUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend fun execute() {
        repository.deleteAllExchangeRates()
    }
}