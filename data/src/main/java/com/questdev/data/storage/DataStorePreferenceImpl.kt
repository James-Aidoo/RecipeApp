package com.questdev.data.storage

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.questdev.domain.model.EmptyResult
import com.questdev.domain.storage.DataStorePreference
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : DataStorePreference {

    private val Context.dataStore by preferencesDataStore(name = PREFERENCE_NAME)

    override suspend fun setThemeIsDark(isDark: Boolean): EmptyResult {
        return context.dataStore.edit { preferences ->
            preferences[KEY_APP_THEME_STATE] = isDark
            Log.d("setThemeIsDark", "New IsDark value saved >> ${preferences[KEY_APP_THEME_STATE]}")
        }.let {
            EmptyResult()
        }
    }

    override fun getThemeIsDark(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_APP_THEME_STATE] ?: false
        }
    }

    companion object {
        private const val PREFERENCE_NAME = "recipe_app_datastore"
        private val KEY_APP_THEME_STATE = booleanPreferencesKey("theme")
    }
}
