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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.presentation.CoinPaprikaViewModel
import com.alberto.coinpaprikatest.presentation.ui.theme.CoinPaprikaTestTheme
import com.alberto.coinpaprikatest.utils.getCoin
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CoinPaprikaList(
    viewModel: CoinPaprikaViewModel = hiltViewModel(),
    navigation: NavController
) {
    val coins: List<Coin> = viewModel.coinsListState.value.data
    val isLoading = viewModel.coinsListState.value.isLoading
    val errorMessage = viewModel.coinsListState.value.errorMessage
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    SwipeReFreshCoinsScreen(
        swipeRefreshState,
        viewModel::getCoins,
        coins,
        navigation
    )
    if (errorMessage.isNotEmpty()) {
        ErrorLabel(errorMessage = errorMessage)
    }
}

@Composable
fun CoinPaprikaListScreen(
    coins: List<Coin>,
    navigation: NavController
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
                    navigation.navigate(Screen.CoinPaprikaDetailedItem.route.plus("/${coin.id}"))
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
            ),
            rememberNavController()
        )
    }
}