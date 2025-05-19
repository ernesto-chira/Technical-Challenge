package com.challenge.exchangerateapp.core.di

import com.challenge.exchangerateapp.core.data.repository.ExchangeRateRepositoryImpl
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository
import org.koin.dsl.module

val coreModule = module {

    single<ExchangeRateRepository> {
        ExchangeRateRepositoryImpl(get(), get())
    }
}