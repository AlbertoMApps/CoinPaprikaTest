package com.alberto.coinpaprikatest.data.remote.model

data class Coin(
    val id: String? = null,
    val name: String? = null,
    val symbol: String? = null,
    val logo: String? = null,
    val tags: List<Tag>? = null,
    val description: String? = null
)