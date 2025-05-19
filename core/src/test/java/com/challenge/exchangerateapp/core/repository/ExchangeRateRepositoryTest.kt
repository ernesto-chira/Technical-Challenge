package com.challenge.exchangerateapp.core.repository

import com.challenge.exchangerateapp.core.data.local.database.dao.ExchangeRateDao
import com.challenge.exchangerateapp.core.data.local.database.entities.ExchangeRateDto
import com.challenge.exchangerateapp.core.data.repository.ExchangeRateRepositoryImpl
import com.challenge.exchangerateapp.core.network.ExchangeRateService
import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity
import com.challenge.exchangerateapp.domain.repository.ExchangeRateRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ExchangeRateRepositoryTest {

    private lateinit var dao: ExchangeRateDao
    private lateinit var exchangeRateService: ExchangeRateService
    private lateinit var repository: ExchangeRateRepository

    @Before
    fun setUp() {
        dao = mock()
        exchangeRateService = mock()
        repository = ExchangeRateRepositoryImpl(exchangeRateService, dao)
    }

    @Test
    fun `when dao returns entities, repository returns mapped data`() = runBlocking {
        val entities = listOf(
            ExchangeRateDto(
                id = 1,
                currency = "EUR",
                currencyExchange = "PEN",
                bidRate = 4.1,
                inputAmount = "100",
                outputAmount = "410",
                date = 1234567890L
            )
        )

        `when`(dao.getAllExchangeRates()).thenReturn(entities)

        var result = listOf<ExchangeRateEntity>()

        repository.getAllExchangeRates {
            result = it
        }

        assertEquals(entities, result)
    }
}