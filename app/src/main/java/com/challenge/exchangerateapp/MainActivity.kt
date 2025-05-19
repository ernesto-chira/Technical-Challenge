package com.challenge.exchangerateapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.challenge.exchangerateapp.navigation.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExchangeRateApp()
        }
    }
}

@Composable
fun ExchangeRateApp() {
    val navController = rememberNavController()
    MaterialTheme {
        Surface {
            NavGraph(navController = navController)
        }
    }
}