package com.challenge.exchangerateapp.core.data.remote.reponses

data class ExchangeRateResponse(
    val success: Boolean,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>,
    val error: ErrorResponse?
)

data class ErrorResponse(
    val code: String,
    val message: String?
)