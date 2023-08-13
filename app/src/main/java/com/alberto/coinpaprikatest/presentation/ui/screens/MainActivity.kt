package com.alberto.coinpaprikatest.presentation.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinPaprikaTestTheme {
                CoinPaprikaNavigation()
            }
        }
    }
}

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
            route = Screen.CoinPaprikaDetailedItem.route.plus("/{coinId}"),
            arguments = listOf(
                navArgument("coinId") {
                    type = NavType.StringType
                }
            )
        ) {
            CoinPaprikaDetailedItem(
                coinId = it.arguments?.getString("coinId")
            )
        }
    }
}

sealed class Screen(val route: String) {
    object CoinPaprikaList : Screen(route = "coin_paprika_list")
    object CoinPaprikaDetailedItem : Screen(route = "coin_paprika_detailed_item")
}