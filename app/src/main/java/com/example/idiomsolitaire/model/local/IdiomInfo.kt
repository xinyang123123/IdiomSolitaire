package com.example.idiomsolitaire.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "idiom_info"
)
data class IdiomInfo(
    @PrimaryKey @ColumnInfo(name = "ID") val id: Int,
    @ColumnInfo(name = "IDIOM") val idiom: String,
    @ColumnInfo(name = "PINYIN") val pinyin: String,
    @ColumnInfo(name = "DESCRIPTION") val description: String?,
    @ColumnInfo(name = "FIRST_PINYIN") val firstPinyin: String,
    @ColumnInfo(name = "LAST_PINYIN") val lastPinyin: String
)
