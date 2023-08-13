package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.presentation.CoinPaprikaViewModel
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme
import com.alberto.coinpaprikatest.utils.getCoin
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CoinPaprikaList(
    viewModel: CoinPaprikaViewModel = hiltViewModel()
) {
    val coins: List<Coin> = viewModel.state.value.data
    val isLoading = viewModel.state.value.isLoading
    val errorMessage = viewModel.state.value.errorMessage
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeReFreshScreen(swipeRefreshState, viewModel::getCoins, coins)
    if (errorMessage.isNotEmpty()) ErrorLabel(errorMessage = errorMessage)
}

@Composable
private fun SwipeReFreshScreen(
    swipeRefreshState: SwipeRefreshState,
    onRefresh: () -> Unit,
    coins: List<Coin>
) {
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh
    ) {
        CoinPaprikaListScreen(coins)
    }
}

@Composable
private fun CoinPaprikaListScreen(
    coins: List<Coin>
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
        ) {
            items(items = coins) { coin ->
                CoinPaprikaListItemScreen(coin, {
                    //Add navigation to the second screen here!
                    coin.id
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CoinPaprikaListScreenPreview() {
    CoinPaprikaTestTheme {
        CoinPaprikaListScreen(
            listOf(
                getCoin(),
                getCoin(),
                getCoin()
            )
        )
    }
}