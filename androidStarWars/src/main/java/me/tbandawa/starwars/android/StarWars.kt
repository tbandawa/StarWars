package me.tbandawa.starwars.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import starwars.data.di.initKoin

class StarWars: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@StarWars)
        }
    }

}