package com.challenge.exchangerateapp.domain.usecase

import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository

class SaveExchangeRecordUseCase(private val repository: ExchangeRateRepository) {

    suspend operator fun invoke(data: ExchangeRateEntity) {
        repository.insertExchangeRate(data)
    }
}