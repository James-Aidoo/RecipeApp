package com.questdev.data.storage.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataClient {

    private const val BASE_URL = "https://food2fork.ca/api/"
    const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"

    val retrofit: Retrofit get() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    private val loggingInterceptor: HttpLoggingInterceptor get() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client: OkHttpClient get() =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

}