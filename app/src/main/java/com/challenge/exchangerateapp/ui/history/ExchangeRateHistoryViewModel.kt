package com.challenge.exchangerateapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.exchangerateapp.domain.usecase.DeleteExchangeRatesUseCase
import com.challenge.exchangerateapp.domain.usecase.GetExchangeRateHistoryUseCase
import com.challenge.exchangerateapp.ui.history.model.ExchangeRateUiModel
import kotlinx.coroutines.launch

class ExchangeRateHistoryViewModel(
    private val getExchangeRateHistoryUseCase: GetExchangeRateHistoryUseCase,
    private val deleteExchangeRatesUseCase: DeleteExchangeRatesUseCase
) : ViewModel() {

    private val _exchangeRates = MutableLiveData<List<ExchangeRateUiModel>>()
    val exchangeRates: LiveData<List<ExchangeRateUiModel>> = _exchangeRates

    init {
        fetchExchangeRates()
    }

    private fun fetchExchangeRates() {
        viewModelScope.launch {
            getExchangeRateHistoryUseCase.execute { exchangeRate ->
                val rates = exchangeRate.map { rate ->
                    ExchangeRateUiModel(
                        currency = rate.currency,
                        currencyExchange = rate.currencyExchange,
                        bidRate = rate.bidRate,
                        inputAmount = rate.inputAmount,
                        outputAmount = rate.outputAmount,
                        date = rate.date,
                    )
                }

                _exchangeRates.postValue(rates)
            }
        }
    }

    fun deleteExchangeRates() {
        viewModelScope.launch {
            deleteExchangeRatesUseCase.execute()
        }
    }
}