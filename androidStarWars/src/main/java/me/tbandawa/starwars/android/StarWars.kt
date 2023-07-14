package me.tbandawa.starwars.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import starwars.data.di.initKoin
import timber.log.Timber

class StarWars: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        initKoin {
            androidContext(this@StarWars)
        }

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }
}