package com.questdev.recipeapp.di

import android.content.Context
import com.questdev.recipeapp.app.App
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    fun provides(@ApplicationContext context: Context): App {
        return context as App
    }

}