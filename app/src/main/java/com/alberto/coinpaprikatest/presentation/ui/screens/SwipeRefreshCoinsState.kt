package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
        AnimatedVisibility(
            visible = !swipeRefreshState.isRefreshing,
            enter = slideInVertically(),
            exit = slideOutVertically()
        ) {
            CoinPaprikaListScreen(coins, navigation)
        }
    }
}