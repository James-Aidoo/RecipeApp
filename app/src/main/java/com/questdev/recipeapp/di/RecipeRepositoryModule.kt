package com.questdev.recipeapp.di

import com.questdev.data.repository.RecipeRepositoryImpl
import com.questdev.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RecipeRepositoryModule {

    @Binds
    abstract fun binds(impl: RecipeRepositoryImpl): RecipeRepository

}
