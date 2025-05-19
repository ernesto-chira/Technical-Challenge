package com.challenge.exchangerateapp.domain.entity

data class ExchangeRate(
    val base: String,
    val symbol: String,
    val rates: Map<String, Double>
)