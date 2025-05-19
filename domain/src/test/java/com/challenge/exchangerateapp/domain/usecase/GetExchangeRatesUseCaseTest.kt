package com.challenge.exchangerateapp.domain.usecase

import com.challenge.exchangerateapp.domain.entity.ExchangeRate
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class GetExchangeRatesUseCaseTest {

    private lateinit var repository: ExchangeRateRepository
    private lateinit var useCase: GetExchangeRateUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = GetExchangeRateUseCase(repository)
    }

    @Test
    fun `when repository returns data, use case returns data`() = runBlocking {
        val exchangeRate = ExchangeRate("EUR", "PEN", mapOf("PEN" to 4.1))
        `when`(repository.getExchangeRate("EUR", "PEN")).thenReturn(exchangeRate)

        val result = useCase.execute("EUR", "PEN")

        assertEquals(exchangeRate, result)
    }
}