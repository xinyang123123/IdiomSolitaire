package com.example.idiomsolitaire.model.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface IdiomDao {

    @Query("SELECT * FROM search_result  WHERE search_result.FIRST_PINYIN =  LOWER(:pinyin)")
    suspend fun searchByFirstPinyin(pinyin: String): List<IdiomResult>
}