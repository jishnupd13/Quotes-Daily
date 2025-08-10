package com.app.quotes_daily.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private const val DATASTORE_NAME = "app_preferences"

private val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

class DataStoreManager(private val context: Context) {

    private object PrefKeys {
        val SAMPLE_SETTING = booleanPreferencesKey("sample_setting")
    }

    val sampleSettingFlow: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[PrefKeys.SAMPLE_SETTING] ?: false
        }

    suspend fun setSampleSetting(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[PrefKeys.SAMPLE_SETTING] = enabled
        }
    }
}
