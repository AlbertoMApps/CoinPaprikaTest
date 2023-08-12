package com.alberto.coinpaprikatest.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CoinTable(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val symbol: String? = null,
    val logo: String? = null,
    val tags: List<TagTable>? = null,
    val description: String? = null
)