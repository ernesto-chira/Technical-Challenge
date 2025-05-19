package com.challenge.exchangerateapp.ui.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.challenge.exchangerateapp.R
import com.challenge.exchangerateapp.ui.components.ShowAlertDialog
import com.challenge.exchangerateapp.ui.history.components.ExchangeRateItem
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRateHistoryScreen() {
    val viewModel: ExchangeRateHistoryViewModel = getViewModel()
    var showDialog by remember { mutableStateOf(false) }

    val exchangeRates by viewModel.exchangeRates.observeAsState(emptyList())

    Scaffold(
        containerColor = Color.White,
        contentColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.bcp_logo),
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                                .padding(top = 24.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                ),
                modifier = Modifier.padding(bottom = 36.dp, top = 12.dp)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(exchangeRates) { rate ->
                    ExchangeRateItem(
                        currency = rate.currency,
                        currencyExchange = rate.currencyExchange,
                        bidRate = rate.bidRate,
                        inputAmount = rate.inputAmount,
                        outputAmount = rate.outputAmount,
                        date = rate.date
                    )
                }
            }

            if (showDialog) {
                ShowAlertDialog(
                    title = stringResource(R.string.str_important),
                    message = stringResource(R.string.str_confirm_clear_history),
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        showDialog = false
                        viewModel.deleteExchangeRates()
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_clear_all_24),
                    contentDescription = stringResource(id = R.string.str_add)
                )
            }
        }
    )
}