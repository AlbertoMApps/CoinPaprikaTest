package com.alberto.coinpaprikatest.data.local.model

import androidx.room.Entity

@Entity
data class TagTable(
    val id: String,
    val name: String
)