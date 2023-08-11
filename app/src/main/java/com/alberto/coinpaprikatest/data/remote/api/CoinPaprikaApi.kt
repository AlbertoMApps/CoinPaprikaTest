package com.alberto.coinpaprikatest.data.remote.api

import com.alberto.coinpaprikatest.data.common.Constants.COIN_PAPRIKA_FEED
import com.alberto.coinpaprikatest.data.remote.model.Coin
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET(COIN_PAPRIKA_FEED)
    suspend fun getCoins(): List<Coin>

    @GET(COIN_PAPRIKA_FEED)
    suspend fun getCoinInfo(@Path("id") id: String? = null): Coin

}