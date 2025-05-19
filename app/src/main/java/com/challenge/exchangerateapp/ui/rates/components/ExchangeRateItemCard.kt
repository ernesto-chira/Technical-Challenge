package com.challenge.exchangerateapp.ui.rates.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.challenge.exchangerateapp.navigation.Screen
import com.challenge.exchangerateapp.ui.rates.CurrencyInfo

@Composable
fun ExchangeRateItemCard(
    navController: NavController,
    currencyBase: String,
    currencyRate: Triple<String, Double, CurrencyInfo?>
) {
    val currencyExchange = currencyRate.first
    val exchange = currencyRate.second
    val currencyInfo: CurrencyInfo? = currencyRate.third
    val flagUrl = currencyInfo?.flagUrl.orEmpty()
    val country = currencyInfo?.country.orEmpty()
    val currencyName = currencyInfo?.currency.orEmpty()
    val description = "1 $currencyBase = $exchange $currencyExchange"

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            navController.navigate(Screen.ExchangeForm.route + "/$currencyExchange/$currencyName") {
                popUpTo(Screen.ExchangeForm.route) { inclusive = true }
            }
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(flagUrl),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )

            Column {
                Text(
                    text = country,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }
        }
    }
}