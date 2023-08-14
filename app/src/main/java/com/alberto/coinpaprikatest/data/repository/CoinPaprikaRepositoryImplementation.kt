package com.alberto.coinpaprikatest.data.repository

import com.alberto.coinpaprikatest.data.local.dao.CoinPaprikaDao
import com.alberto.coinpaprikatest.data.mappers.toCoin
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

    /**
     * This operation will display coins filtered by tag sorted by name and saved in cache.
     */
    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())

        try {
            val coinsInfo = getCoinsInfo()
            coinPaprikaDao.deleteCoinTableList()
            coinPaprikaDao.insertCoinTableList(coinsInfo.toCoinsTable())

        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Coins could not be loaded"))
        } catch (e: IOException) {
            emit(Resource.Error(message = "Check your internet connection"))
        }
        val coinPaprikaTableList = coinPaprikaDao.getCoinTableList()
        if (coinPaprikaTableList.isNotEmpty()) {
            emit(Resource.Success(data = coinPaprikaTableList.toCoins()))
        }

    }

    /**
     * This function returns a new list of coins with all the information filtered by tags and sorted by name
     */
    private suspend fun getCoinsInfo(): List<Coin> {
        val coinsInfo = arrayListOf<Coin>()
        //Here we just limit the numbers of coins to show, because there's a limit of 60 requests per hour in the free version of the API.
        val coins = coinPaprikaApi.getCoins().take(10)
        coins.forEach { coin ->
            val coinInfo = coinPaprikaApi.getCoinInfo(coin.id)
            coinsInfo.add(coinInfo)
        }
        return coinsInfo
            .filter { !it.tags.isNullOrEmpty() }
            .sortedBy { it.name }
    }

    /**
     *This operation will get a coin by Id from the list of coins previously cached in the database.
     */
    override fun getCoin(id: String): Flow<Resource<Coin>> = flow {
        val coin = coinPaprikaDao.getCoinTable(id).toCoin()
        emit(Resource.Success(data = coin))
    }
}