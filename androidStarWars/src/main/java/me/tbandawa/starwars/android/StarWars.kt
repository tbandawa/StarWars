package me.tbandawa.starwars.android

import android.app.Application
import me.tbandawa.starwars.android.di.appModule
import org.koin.android.ext.koin.androidContext
import starwars.data.di.initKoin
import timber.log.Timber

class StarWars: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        initKoin {
            androidContext(this@StarWars)
            modules(listOf(appModule))
        }

        // enable Timber in debug mode
        //if (BuildConfig.DEBUG)
        //    Timber.plant(Timber.DebugTree())

    }
}