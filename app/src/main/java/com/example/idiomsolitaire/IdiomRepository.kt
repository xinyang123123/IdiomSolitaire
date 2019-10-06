package com.example.idiomsolitaire

import com.example.idiomsolitaire.model.local.IdiomDatabase
import com.example.idiomsolitaire.model.local.IdiomResult

class IdiomRepository {

    private val idiomDatabase : IdiomDatabase = IdiomDatabase.getInstance(App.instance)

    suspend fun searchIdiomByFirstPinyin(firstPinyin:String) : List<IdiomResult> {
       return idiomDatabase.getIdiomDat().searchByFirstPinyin(firstPinyin)
    }
}