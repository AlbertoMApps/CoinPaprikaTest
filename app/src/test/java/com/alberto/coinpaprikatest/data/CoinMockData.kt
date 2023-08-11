package com.alberto.coinpaprikatest.data

import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.data.remote.model.Tag

fun getCoin() =
    Coin(
        id = "btc-bitcoin",
        name = "Bitcoin",
        symbol = "BTC",
        logo = "https://static.coinpaprika.com/coin/bnb-binance-coin/logo.png",
        tags = getTags()
    )

private fun getTags() =
    listOf<Tag>(
        Tag(
            id = "blockchain-service",
            name = "Blockchain Service"
        )
    )