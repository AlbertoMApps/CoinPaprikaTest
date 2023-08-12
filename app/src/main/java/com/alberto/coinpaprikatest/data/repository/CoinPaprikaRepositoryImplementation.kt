package com.alberto.coinpaprikatest.data.repository

import com.alberto.coinpaprikatest.data.local.dao.CoinPaprikaDao
import com.alberto.coinpaprikatest.data.mappers.toCoins
import com.alberto.coinpaprikatest.data.mappers.toCoinsTable
import com.alberto.coinpaprikatest.data.remote.api.CoinPaprikaApi
import com.alberto.coinpaprikatest.data.remote.model.Coin
import com.alberto.coinpaprikatest.domain.CoinRepositoryService
import com.alberto.coinpaprikatest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinPaprikaRepositoryImplementation @Inject constructor(
    private val coinPaprikaApi: CoinPaprikaApi,
    private val coinPaprikaDao: CoinPaprikaDao
) : CoinRepositoryService {

    /*
    * This operation will display coins filtered by tag sorted by name and saved in cache.
     */
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        val coinPaprikaTableList = coinPaprikaDao.getCoinTableList()
        emit(Resource.Loading())
        if (coinPaprikaTableList.isNotEmpty()) {
            emit(Resource.Loading(coinPaprikaDao.getCoinTableList().toCoins()))
        }

        try {
            val coinsInfo = arrayListOf<Coin>()
            val coins = coinPaprikaApi.getCoins()
            coins.forEach { coin ->
                val coinInfo = coinPaprikaApi.getCoinInfo(coin.id)
                coinsInfo.add(coinInfo)
            }
            coinsInfo
                .filter { !it.tags.isNullOrEmpty() }
                .sortedBy { it.name }
            coinPaprikaDao.deleteCoinTableList()
            coinPaprikaDao.insertCoinTableList(coinsInfo.toCoinsTable())
            emit(Resource.Success(data = coinPaprikaTableList.toCoins()))

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }

    }

    /*
    * This operation will get a coin by Id from the list of coins previously cached in the database.
     */
    override fun getCoin(id: String) {

    }
}