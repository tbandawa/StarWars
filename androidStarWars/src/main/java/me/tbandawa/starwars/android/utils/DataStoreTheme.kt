package me.tbandawa.starwars.android.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreTheme(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("appTheme")
        val APP_THEME_KEY = booleanPreferencesKey("app_theme")
    }

    val isDarkTheme: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[APP_THEME_KEY] ?: false
        }

    suspend fun saveTheme(isDarkTheme: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[APP_THEME_KEY] = isDarkTheme
        }
    }
}