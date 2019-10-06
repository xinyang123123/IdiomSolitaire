package com.example.idiomsolitaire

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

class App : Application() {

    companion object {
        var instance: Context by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext
    }
}