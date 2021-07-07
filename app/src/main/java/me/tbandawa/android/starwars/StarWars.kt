package me.tbandawa.android.starwars

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Subclass of [Application] with @HiltAndroidApp that triggers Hilt's code
 * generation and adds an application-level dependency container.
 *
 * Also, sets up Timber in the DEBUG BuildConfig.
 */
@HiltAndroidApp
class StarWars : Application() {

    override fun onCreate() {
        super.onCreate()

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }
}