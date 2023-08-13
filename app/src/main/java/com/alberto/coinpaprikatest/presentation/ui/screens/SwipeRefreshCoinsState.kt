package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SwipeReFreshCoinsScreen(
    swipeRefreshState: SwipeRefreshState,
    onRefresh: () -> Unit,
    coins: List<Coin>,
    navigation: NavController
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        CoinPaprikaListScreen(coins, navigation)
    }
}