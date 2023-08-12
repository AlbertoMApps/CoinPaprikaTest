package com.alberto.coinpaprikatest.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.alberto.coinpaprikatest.data.local.converters.TagConverter
import com.alberto.coinpaprikatest.data.local.dao.CoinPaprikaDao
import com.alberto.coinpaprikatest.data.local.model.CoinTable


@TypeConverters(TagConverter::class)
@Database(
    entities = [CoinTable::class],
    version = 1,
    exportSchema = false
)

abstract class CoinPaprikaDatabase : RoomDatabase() {
    abstract fun getCoinPaprikaDao(): CoinPaprikaDao
}