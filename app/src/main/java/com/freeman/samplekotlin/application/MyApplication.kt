package com.freeman.samplekotlin.application

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class MyApplication: MultiDexApplication() {

    companion object{
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        instance = this
        MultiDex.install(this)
        super.onCreate()

    }

    override fun onTerminate() {
        super.onTerminate()
    }
}