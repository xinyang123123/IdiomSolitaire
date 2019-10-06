package com.example.idiomsolitaire.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "idiom_count")
data class IdiomCount(
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int,
    @ColumnInfo(name = "PINYIN") val pinyin: String,
    @ColumnInfo(name = "COUNT") val count: Int
)