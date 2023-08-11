package com.alberto.coinpaprikatest.domain

import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinRepositoryService {
    fun getCoins(): Flow<Resource<List<Coin>>>
    fun getCoin(id: String)
}