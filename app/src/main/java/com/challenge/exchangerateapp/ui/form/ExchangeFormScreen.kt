package com.challenge.exchangerateapp.ui.form

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.challenge.exchangerateapp.R
import com.challenge.exchangerateapp.navigation.Screen
import com.challenge.exchangerateapp.ui.form.components.ExchangeFormAmountsCard
import com.challenge.exchangerateapp.ui.form.components.ExchangeFormButton
import com.challenge.exchangerateapp.ui.theme.BlueLight
import org.koin.androidx.compose.getViewModel

@SuppressLint("DefaultLocale")
@Composable
fun ExchangeFormScreen(
    navController: NavController,
    currency: String?,
    currencyName: String?
) {
    val viewModel: ExchangeFormViewModel = getViewModel()
    val currencyBase = "EUR"
    val currencySelected = currency.orEmpty()
    val currencyNameSelected = currencyName.orEmpty()

    LaunchedEffect(key1 = currency) {
        currency?.let {
            viewModel.fetchExchangeRates(currencyBase, currency)
        }
    }

    val inputAmount by viewModel.inputValue.observeAsState()
    val outputAmount by viewModel.resultValue.observeAsState()
    val rate by viewModel.exchangeRate.observeAsState()
    val bidRate by viewModel.bidRateValue.observeAsState()
    val askRate by viewModel.askRateValue.observeAsState()

    Scaffold(
        bottomBar = {
            Column {
                ExchangeFormButton(
                    title = stringResource(id = R.string.str_start_your_operation),
                    color = BlueLight,
                    onClick = {
                        viewModel.calculateResult()

                        viewModel.saveExchangeRate(
                            currency = currencyBase,
                            currencyExchange = currencySelected,
                            bidRate = bidRate ?: 1.0,
                            inputAmount = inputAmount.orEmpty(),
                            outputAmount = outputAmount.orEmpty(),
                            date = System.currentTimeMillis()
                        )
                    }
                )

                ExchangeFormButton(
                    title = stringResource(id = R.string.str_see_history),
                    color = Color.Black,
                    onClick = {
                        navController.navigate(Screen.ExchangeRatesHistory.route) {
                            popUpTo(Screen.ExchangeForm.route)
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.bcp_logo),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ExchangeFormAmountsCard(
                currency = currencySelected,
                currencyName = currencyNameSelected,
                viewModel = viewModel,
                onActionLongPress = { route ->
                    navController.navigate(route) {
                        popUpTo(Screen.ExchangeForm.route)
                    }
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                rate?.let {
                    val bidRateFormat = String.format("%.2f", bidRate).toDouble()
                    val askRateFormat = String.format("%.2f", askRate).toDouble()
                    Text(
                        text = stringResource(
                            id = R.string.str_bid_and_ask,
                            bidRateFormat,
                            askRateFormat
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                }

            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

