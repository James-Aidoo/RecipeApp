package com.questdev.recipeapp.di

import com.questdev.data.storage.remote.RemoteDataClient
import com.questdev.data.storage.remote.RemoteDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataServiceModule {

    @Provides
    fun providesRemoteDataService(): RemoteDataService {
        return RemoteDataClient.retrofit.create(RemoteDataService::class.java)
    }

    @Provides
    @Named("token")
    fun providesToken() = RemoteDataClient.TOKEN

}