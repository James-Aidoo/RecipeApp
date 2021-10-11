package com.questdev.recipeapp.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.questdev.data.storage.DataStorePreferenceImpl
import com.questdev.domain.storage.DataStorePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataStorePreferenceModule {

    private val Context.dataStore by preferencesDataStore(name = PREFERENCE_NAME)

    @Provides
    fun binds(@ApplicationContext context: Context): DataStorePreference {
        return DataStorePreferenceImpl(context.dataStore)
    }

    companion object {
        private const val PREFERENCE_NAME = "recipe_app_datastore"
    }

}
