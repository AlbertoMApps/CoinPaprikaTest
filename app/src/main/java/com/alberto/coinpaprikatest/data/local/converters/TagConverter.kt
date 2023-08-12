package com.alberto.coinpaprikatest.data.local.converters

import androidx.room.TypeConverter
import com.alberto.coinpaprikatest.data.local.model.TagTable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class TagConverter {

    private val gson = Gson()
    private val type: Type = object : TypeToken<List<TagTable>>() {}.type

    @TypeConverter
    fun fromTagList(tagTableList: List<TagTable>?): String? {
        if (tagTableList == null) {
            return null
        }
        return gson.toJson(tagTableList, type)
    }

    @TypeConverter
    fun toTagList(tagTableListString: String?): List<TagTable>? {
        if (tagTableListString == null) {
            return null
        }
        return gson.fromJson<List<TagTable>>(tagTableListString, type)
    }
}