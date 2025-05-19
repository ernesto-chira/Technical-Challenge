package com.challenge.exchangerateapp.domain.di

import com.challenge.exchangerateapp.domain.usecase.DeleteExchangeRatesUseCase
import com.challenge.exchangerateapp.domain.usecase.GetExchangeRateHistoryUseCase
import com.challenge.exchangerateapp.domain.usecase.GetExchangeRateUseCase
import com.challenge.exchangerateapp.domain.usecase.SaveExchangeRecordUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetExchangeRateUseCase(get()) }
    single { SaveExchangeRecordUseCase(get()) }
    single { GetExchangeRateHistoryUseCase(get()) }
    single { DeleteExchangeRatesUseCase(get()) }
}