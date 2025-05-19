package com.challenge.exchangerateapp.ui.history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.challenge.exchangerateapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ExchangeRateItem(
    currency: String,
    currencyExchange: String,
    bidRate: Double,
    inputAmount: String,
    outputAmount: String,
    date: Long
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
        ) {
            Row {
                Text(
                    text = currency,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .height(16.dp)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 4.dp)
                )

                Text(
                    text = currencyExchange,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                Text(
                    text = stringResource(id = R.string.str_bid_exchange, ":"),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "1 $currency = $bidRate $currencyExchange",
                    fontSize = 12.sp,
                )
            }

            Row {
                Text(
                    text = stringResource(id = R.string.str_you_send, ":"),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$inputAmount $currency",
                    fontSize = 12.sp,
                )
            }

            Row {
                Text(
                    text = stringResource(id = R.string.str_you_receive, ":"),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "$outputAmount $currencyExchange",
                    fontSize = 12.sp,
                )
            }

            Row {
                Text(
                    text = stringResource(id = R.string.str_operation_date),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = formatTimestamp(date),
                    fontSize = 12.sp,
                )
            }
        }
    }
}

fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return format.format(date)
}