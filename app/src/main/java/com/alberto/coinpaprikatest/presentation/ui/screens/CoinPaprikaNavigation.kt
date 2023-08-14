package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

private const val ARG_COIN_ID = "coinId"

@Composable
fun CoinPaprikaNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CoinPaprikaList.route
    ) {
        composable(route = Screen.CoinPaprikaList.route) {
            CoinPaprikaList(navigation = navController)
        }
        composable(
            route = Screen.CoinPaprikaDetailedItem.route
                .plus("/{$ARG_COIN_ID}"),
            arguments = getCoinArguments(ARG_COIN_ID)
        ) {
            CoinPaprikaDetailedItem(
                coinId = it.arguments?.getString(ARG_COIN_ID)
            )
        }
    }
}

private fun getCoinArguments(argumentName: String): List<NamedNavArgument> =
    listOf(
        navArgument(argumentName) {
            type = NavType.StringType
        }
    )

sealed class Screen(val route: String) {
    object CoinPaprikaList : Screen(route = "coin_paprika_list")
    object CoinPaprikaDetailedItem : Screen(route = "coin_paprika_detailed_item")
}