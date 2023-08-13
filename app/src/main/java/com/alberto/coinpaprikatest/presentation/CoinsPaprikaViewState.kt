package com.alberto.coinpaprikatest.presentation

import com.alberto.coinpaprikatest.data.remote.model.Coin

data class CoinsPaprikaViewState(
    val isLoading: Boolean = false,
    val data: List<Coin> = listOf(),
    val errorMessage: String = ""
)

data class CoinPaprikaViewState(
    val isLoading: Boolean = false,
    val data: Coin = Coin(),
    val errorMessage: String = ""
)