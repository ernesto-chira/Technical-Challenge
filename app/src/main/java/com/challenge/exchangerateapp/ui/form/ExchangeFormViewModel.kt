package com.challenge.exchangerateapp.ui.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.exchangerateapp.domain.entity.ExchangeRate
import com.challenge.exchangerateapp.domain.entity.ExchangeRateEntity
import com.challenge.exchangerateapp.domain.usecase.GetExchangeRateUseCase
import com.challenge.exchangerateapp.domain.usecase.SaveExchangeRecordUseCase
import kotlinx.coroutines.launch

class ExchangeFormViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val saveExchangeRateUseCase: SaveExchangeRecordUseCase
) : ViewModel() {

    private val margin = 0.005

    private val _inputValue = MutableLiveData("")
    val inputValue: LiveData<String> = _inputValue

    private val _resultValue = MutableLiveData("")
    val resultValue: LiveData<String> = _resultValue

    private val _bidRateValue = MutableLiveData(1.0)
    val bidRateValue: LiveData<Double> = _bidRateValue

    private val _askRateValue = MutableLiveData(1.0)
    val askRateValue: LiveData<Double> = _askRateValue

    private val _exchangeRate = MutableLiveData<ExchangeRate>()
    val exchangeRate: LiveData<ExchangeRate> = _exchangeRate

    fun fetchExchangeRates(base: String, symbol: String) {
        viewModelScope.launch {
            val result = getExchangeRateUseCase.execute(base, symbol)
            val rate = result.rates[symbol] ?: 0.0
            val bidRate = formatDouble(rate - (rate * margin)).toDouble()
            val askRate = formatDouble(rate + (rate * margin)).toDouble()
            _bidRateValue.value = bidRate
            _askRateValue.value = askRate
            _exchangeRate.value = result
        }
    }

    fun saveExchangeRate(
        currency: String,
        currencyExchange: String,
        bidRate: Double,
        inputAmount: String,
        outputAmount: String,
        date: Long
    ) {
        viewModelScope.launch {
            val data = ExchangeRateEntity(
                id = null,
                currency = currency,
                currencyExchange = currencyExchange,
                bidRate = bidRate,
                inputAmount = inputAmount,
                outputAmount = outputAmount,
                date = date
            )

            saveExchangeRateUseCase(data)
        }
    }

    fun updateInputValue(newValue: String) {
        _inputValue.value = newValue
    }

    fun calculateResult() {
        val input = _inputValue.value ?: ""
        val bidRate = _bidRateValue.value ?: 1.0
        _resultValue.value = try {
            val value = input.toDouble()
            formatDouble(value * bidRate)
        } catch (e: NumberFormatException) {
            "Error en el formato"
        }
    }

    private fun formatDouble(value: Double): String {
        return String.format("%.2f", value)
    }
}