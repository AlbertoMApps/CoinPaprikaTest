package com.alberto.coinpaprikatest.data.remote.model

data class Coin(
    private val id: String? = null,
    private val name: String? = null,
    private val symbol: String? = null,
    private val logo: String? = null,
    private val tags: List<Tag>,
    private val description: String? = null
)