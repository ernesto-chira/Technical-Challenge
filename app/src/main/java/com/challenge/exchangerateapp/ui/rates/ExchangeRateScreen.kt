package com.challenge.exchangerateapp.ui.rates

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.challenge.exchangerateapp.R
import com.challenge.exchangerateapp.ui.rates.components.ExchangeRateItemCard
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExchangeRateScreen(
    navController: NavController,
    currencyBase: String
) {
    val viewModel: ExchangeRateViewModel = getViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchExchangeRates(context, "EUR")
    }

    val currencyData by viewModel.currencyData.observeAsState(emptyList())

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
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(currencyData.size) { index ->
                    ExchangeRateItemCard(
                        navController = navController,
                        currencyBase = currencyBase,
                        currencyRate = currencyData[index]
                    )
                }
            }
        }
    )
}

