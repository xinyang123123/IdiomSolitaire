package com.example.idiomsolitaire.model.local

import androidx.room.Dao
import androidx.room.Query

@Dao
interface IdiomDao {

    @Query("SELECT * FROM search_result  WHERE search_result.FIRST_PINYIN =  LOWER(:pinyin) ORDER BY search_result.COUNT")
    suspend fun searchByFirstPinyin(pinyin: String): List<IdiomResult>
}