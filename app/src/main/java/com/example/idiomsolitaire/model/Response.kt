package com.example.idiomsolitaire.model

data class Response<T>(val data: T, val errorCode: Int, val errorMsg: String)