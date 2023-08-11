package com.alberto.coinpaprikatest.di

import android.util.Log
import com.alberto.coinpaprikatest.data.common.Constants.COIN_PAPRIKA_BASE_URL
import com.alberto.coinpaprikatest.data.remote.api.CoinPaprikaApi
import com.alberto.coinpaprikatest.data.repository.CoinPaprikaRepositoryImplementation
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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

    @Provides
    @Singleton
    fun getGson(): Gson =
        GsonBuilder()
            .serializeNulls()
            .setLenient()
            .create()

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("Logger", message) }
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideCoinPaprikaRepositoryImplementation(
        api: CoinPaprikaApi
    ): CoinRepositoryService =
        CoinPaprikaRepositoryImplementation(
            api
        )

}