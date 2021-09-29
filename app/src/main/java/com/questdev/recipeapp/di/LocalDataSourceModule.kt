package com.questdev.recipeapp.di

import com.questdev.data.storage.source.LocalDataSourceImpl
import com.questdev.domain.storage.source.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun binds(impl: LocalDataSourceImpl): LocalDataSource

}
