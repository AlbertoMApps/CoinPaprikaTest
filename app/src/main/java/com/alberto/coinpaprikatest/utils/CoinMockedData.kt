package com.alberto.coinpaprikatest.utils

import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.data.remote.model.Tag

fun getCoin() = Coin(
    id = "btc-bitcoin",
    name = "Bitcoin",
    symbol = "BTC",
    logo = "https://static.coinpaprika.com/coin/bnb-binance-coin/logo.png",
    description = "Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.",
    tags = listOf(
        Tag(
            id = "blockchain-service",
            name = "Blockchain Service"
        )
    )
)