package com.challenge.exchangerateapp.core.network

import com.challenge.exchangerateapp.core.data.remote.reponses.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRateService {
    @GET("latest")
    suspend fun getExchangeRate(
        @Query("base") base: String,
        @Query("access_key") apiKey: String
    ): ExchangeRateResponse
}