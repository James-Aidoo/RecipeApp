package com.questdev.data.storage.remote

import com.questdev.data.model.RecipeDto
import com.questdev.data.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RemoteDataService {

    @GET("search")
    suspend fun searchRecipe(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse?

    @GET("get")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto?

}