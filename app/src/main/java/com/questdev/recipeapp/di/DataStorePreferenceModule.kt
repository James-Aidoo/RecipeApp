package com.questdev.recipeapp.di

import com.questdev.data.storage.DataStorePreferenceImpl
import com.questdev.domain.storage.DataStorePreference
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataStorePreferenceModule {

    @Binds
    abstract fun binds(impl: DataStorePreferenceImpl): DataStorePreference

}
