package com.challenge.exchangerateapp.di

import com.challenge.exchangerateapp.ui.form.ExchangeFormViewModel
import com.challenge.exchangerateapp.ui.history.ExchangeRateHistoryViewModel
import com.challenge.exchangerateapp.ui.rates.ExchangeRateViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ExchangeFormViewModel(get(), get()) }
    viewModel { ExchangeRateViewModel(get()) }
    viewModel { ExchangeRateHistoryViewModel(get(), get()) }
}