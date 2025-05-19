package com.challenge.exchangerateapp.ui.form.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.challenge.exchangerateapp.R
import com.challenge.exchangerateapp.navigation.Screen
import com.challenge.exchangerateapp.ui.components.ShowAlertDialog
import com.challenge.exchangerateapp.ui.form.ExchangeFormViewModel
import kotlinx.coroutines.launch

@Composable
fun ExchangeFormAmountsCard(
    currency: String,
    currencyName: String,
    viewModel: ExchangeFormViewModel,
    onActionLongPress: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val inputValue by viewModel.inputValue.observeAsState("")
    val result by viewModel.resultValue.observeAsState("")
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .weight(3f)
                ) {
                    TextField(
                        value = inputValue,
                        onValueChange = { viewModel.updateInputValue(it) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.str_you_send, "")) }
                    )
                }

                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .weight(2f)
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    showDialog = true
                                }
                            )
                        }
                ) {
                    Text(text = "Euros", style = MaterialTheme.typography.bodyLarge, color = Color.White)
                }

                if (showDialog) {
                    ShowAlertDialog(
                        title = stringResource(id = R.string.str_important),
                        message = stringResource(id = R.string.str_the_plan_only_allows_euros_message),
                        onDismiss = { showDialog = false },
                        onConfirm = { showDialog = false }
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxHeight()
                ) {
                    TextField(
                        value = result,
                        onValueChange = { },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            cursorColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        ),
                        readOnly = true,
                        label = { Text(text = stringResource(id = R.string.str_you_receive, "")) }
                    )
                }
                Column(
                    modifier = Modifier
                        .background(Color.Black)
                        .weight(2f)
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    scope.launch {
                                        onActionLongPress(Screen.ExchangeRates.route + "/" + currency)
                                    }
                                }
                            )
                        }
                ) {
                    Text(text = currencyName, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                }
            }
        }

        Button(
            onClick = { },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            modifier = Modifier
                .size(32.dp)
                .align(Alignment.CenterEnd)
                .offset(x = (-10).dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_sync_24),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}