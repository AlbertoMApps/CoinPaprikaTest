package com.alberto.coinpaprikatest.di

import com.alberto.coinpaprikatest.data.common.Constants.COIN_PAPRIKA_BASE_URL
import com.alberto.coinpaprikatest.data.remote.api.CoinPaprikaApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinPaprikaModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(
        client: OkHttpClient,
        gson: Gson
    ): CoinPaprikaApi =
        Retrofit.Builder()
            .baseUrl(COIN_PAPRIKA_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(CoinPaprikaApi::class.java)

}