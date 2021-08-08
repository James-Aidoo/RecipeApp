package com.questdev.data.storage.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataClient {

    private const val BASE_URL = "https://food2fork.ca/api/"
    const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

    val retrofit: Retrofit get() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}