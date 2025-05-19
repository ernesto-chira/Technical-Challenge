package com.challenge.exchangerateapp.ui.rates

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.challenge.exchangerateapp.domain.usecase.GetExchangeRateUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch

class ExchangeRateViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase
) : ViewModel() {

    private val _currencyData = MutableLiveData<List<Triple<String, Double, CurrencyInfo?>>>()
    val currencyData: LiveData<List<Triple<String, Double, CurrencyInfo?>>> = _currencyData

    fun fetchExchangeRates(context: Context, base: String) {
        viewModelScope.launch {
            val result = getExchangeRateUseCase.execute(base = base)
            val localData = readJsonFile(context)
            val combineData = combineData(result.rates, localData)
            _currencyData.value = combineData
        }
    }

    private fun combineData(
        serviceData: Map<String, Double>,
        localData: Map<String, CurrencyInfo>
    ): List<Triple<String, Double, CurrencyInfo?>> {
        return serviceData.map { (currencyCode, rate) ->
            val currencyInfo = localData[currencyCode]
            Triple(currencyCode, rate, currencyInfo)
        }
    }

    private fun readJsonFile(context: Context): Map<String, CurrencyInfo> {
        val jsonString = context.assets.open("flags.json").bufferedReader().use { it.readText() }
        val type = object : TypeToken<Map<String, CurrencyInfo>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}

data class CurrencyInfo(
    val country: String,
    val currency: String,
    val flagUrl: String
)