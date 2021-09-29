package com.questdev.recipeapp.di

import com.questdev.data.repository.DataRepositoryImpl
import com.questdev.domain.repository.DataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataRepositoryModule {

    @Binds
    abstract fun binds(impl: DataRepositoryImpl): DataRepository

}
