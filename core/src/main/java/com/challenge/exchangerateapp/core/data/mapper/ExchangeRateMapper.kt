package com.challenge.exchangerateapp.core.data.mapper

import com.challenge.exchangerateapp.core.data.local.database.entities.ExchangeRateDto
import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity

fun ExchangeRateDto.toExchangeRateEntity(): ExchangeRateEntity {
    return ExchangeRateEntity(
        id = this.id,
        currency = this.currency,
        currencyExchange = this.currencyExchange,
        bidRate = this.bidRate,
        inputAmount = this.inputAmount,
        outputAmount = this.outputAmount,
        date = this.date
    )
}

fun ExchangeRateEntity.toExchangeRateDto(): ExchangeRateDto {
    return ExchangeRateDto(
        currency = this.currency,
        currencyExchange = this.currencyExchange,
        bidRate = this.bidRate,
        inputAmount = this.inputAmount,
        outputAmount = this.outputAmount,
        date = this.date,
    )
}