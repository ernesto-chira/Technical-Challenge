package com.challenge.exchangerateapp.domain.exceptions

open class DomainException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)

class InvalidCurrencyException(message: String? = "Invalid currency") : DomainException(message)

class ExchangeRateNotFoundException(message: String? = "Exchange rate not found") : DomainException(message)