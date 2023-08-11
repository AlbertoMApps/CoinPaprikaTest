package com.alberto.coinpaprikatest.data.remote.api

import com.alberto.coinpaprikatest.data.common.Constants.COIN_PAPRIKA_FEED
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET(COIN_PAPRIKA_FEED)
    suspend fun getCoins(@Path("id") id: String)
}