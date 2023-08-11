package com.alberto.coinpaprikatest.presentation.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.presentation.CoinPaprikaViewModel

@Composable
fun CoinPaprikaList(
    viewModel: CoinPaprikaViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val coins: List<Coin> = viewModel.state.value.data
    viewModel.getCoins()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
            items(items = coins) { coin ->
                CoinPaprikaListItem(coin)
            }
        }
    }

}