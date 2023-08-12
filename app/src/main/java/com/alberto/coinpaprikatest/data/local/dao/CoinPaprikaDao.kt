package com.alberto.coinpaprikatest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alberto.coinpaprikatest.data.local.model.CoinTable

@Dao
interface CoinPaprikaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoinTableList(coins: List<CoinTable>)

    @Query("Delete from cointable")
    suspend fun deleteCoinTableList()

    @Query("Select * from cointable")
    suspend fun getCoinTableList(): List<CoinTable>

    @Query("Select * from cointable where id=:id")
    suspend fun getCoinTable(id: String): CoinTable
}