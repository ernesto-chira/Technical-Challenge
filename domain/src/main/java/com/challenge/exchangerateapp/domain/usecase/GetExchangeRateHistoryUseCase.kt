package com.challenge.exchangerateapp.domain.usecase

import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository

class GetExchangeRateHistoryUseCase(
    private val repository: ExchangeRateRepository
) {
    suspend fun execute(callback: (List<ExchangeRateEntity>) -> Unit) {
        return repository.getAllExchangeRates(callback)
    }
}