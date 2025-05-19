package com.challenge.exchangerateapp.domain.entity

data class ExchangeRateEntity(
    val id: Long?,
    val currency: String,
    val currencyExchange: String,
    val bidRate: Double,
    val inputAmount: String,
    val outputAmount: String,
    val date: Long
)