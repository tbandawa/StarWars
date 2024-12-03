package me.tbandawa.starwars.android.di

import me.tbandawa.starwars.android.utils.DataStoreTheme
import org.koin.dsl.module

val appModule = module {
    factory { DataStoreTheme(get()) }
}