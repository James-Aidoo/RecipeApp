package com.questdev.data.storage

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.questdev.domain.model.EmptyResult
import com.questdev.domain.storage.DataStorePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStorePreference {

    override suspend fun setThemeIsDark(isDark: Boolean): EmptyResult {
        return dataStore.edit { preferences ->
            preferences[KEY_APP_THEME_STATE] = isDark
            Log.d("setThemeIsDark", "New IsDark value saved >> ${preferences[KEY_APP_THEME_STATE]}")
        }.let {
            EmptyResult()
        }
    }

    override fun getThemeIsDark(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_APP_THEME_STATE] ?: false
        }
    }

    companion object {
        private val KEY_APP_THEME_STATE = booleanPreferencesKey("theme")
    }
}
