package com.alberto.coinpaprikatest.data.mappers

import com.alberto.coinpaprikatest.data.local.model.CoinTable
import com.alberto.coinpaprikatest.data.local.model.TagTable
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.data.remote.model.Tag

fun List<Coin>.toCoinsTable() =
    map { it.toCoinTable() }

fun Coin.toCoinTable() =
    CoinTable(
        id = id ?: "",
        name = name,
        symbol = symbol,
        logo = logo,
        tags = tags?.toTagTable(),
        description = description
    )

fun List<Tag>.toTagTable() =
    map { it.toTagTableList() }

fun Tag.toTagTableList() =
    TagTable(
        id = id,
        name = name
    )

fun List<CoinTable>.toCoins() =
    map { it.toCoin() }

fun CoinTable.toCoin() =
    Coin(
        id = id,
        name = name,
        symbol = symbol,
        logo = logo,
        tags = tags?.toTagList(),
        description = description
    )

fun List<TagTable>.toTagList() =
    map { it.toTag() }

fun TagTable.toTag() =
    Tag(
        id = id,
        name = name
    )