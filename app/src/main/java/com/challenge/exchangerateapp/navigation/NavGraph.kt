package com.challenge.exchangerateapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.challenge.exchangerateapp.ui.form.ExchangeFormScreen
import com.challenge.exchangerateapp.ui.history.ExchangeRateHistoryScreen
import com.challenge.exchangerateapp.ui.rates.ExchangeRateScreen
import com.challenge.exchangerateapp.ui.splash.SplashScreen

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object ExchangeForm : Screen("exchange_form")
    data object ExchangeRates : Screen("exchange_rates")
    data object ExchangeRatesHistory : Screen("exchange_rates_history")
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(
            Screen.ExchangeForm.route + "/{param}/{currencyName}",
            arguments = listOf(
                navArgument("param") { defaultValue = "PEN" },
                navArgument("currencyName") { defaultValue = "Soles" }
            )
        ) { backStackEntry ->
            val param = backStackEntry.arguments?.getString("param")
            val currencyName = backStackEntry.arguments?.getString("currencyName")
            ExchangeFormScreen(navController, param, currencyName)
        }
        composable(
            Screen.ExchangeRates.route + "/{param}",
            arguments = listOf(navArgument("param") { type = NavType.StringType} )
        ) { backStackEntry ->
            val param = backStackEntry.arguments?.getString("param").orEmpty()
            ExchangeRateScreen(
                navController = navController,
                currencyBase = param
            )
        }
        composable(Screen.ExchangeRatesHistory.route) {
            ExchangeRateHistoryScreen()
        }
    }
}