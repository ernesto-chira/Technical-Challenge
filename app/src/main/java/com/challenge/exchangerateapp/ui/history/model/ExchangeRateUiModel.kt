package com.challenge.exchangerateapp.ui.history.model

data class ExchangeRateUiModel(
    val currency: String,
    val currencyExchange: String,
    val bidRate: Double,
    val inputAmount: String,
    val outputAmount: String,
    val date: Long
)