package com.example.idiomsolitaire.model.local

import androidx.room.ColumnInfo
import androidx.room.DatabaseView

@DatabaseView(viewName = "search_result",value = "SELECT idiom_info.IDIOM, idiom_info.PINYIN ,idiom_info.FIRST_PINYIN,idiom_info.DESCRIPTION, idiom_count.COUNT FROM idiom_count INNER JOIN idiom_info ON idiom_info.LAST_PINYIN = idiom_count.PINYIN ")
data class IdiomResult(
    @ColumnInfo(name = "IDIOM") val idiom: String,
    @ColumnInfo(name = "PINYIN") val pinyin: String,
    @ColumnInfo(name = "FIRST_PINYIN") val firstPinyin: String,
    @ColumnInfo(name = "DESCRIPTION") val description: String?,
    @ColumnInfo(name = "COUNT") val count: Int
)