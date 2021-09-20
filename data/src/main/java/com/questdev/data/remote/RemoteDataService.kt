package com.questdev.data.remote

import com.questdev.domain.model.RecipeDto
import com.questdev.domain.model.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RemoteDataService {

    @GET("recipe/search")
    suspend fun searchRecipe(
        @Header("Authorization") token: String,
        @Query("page") page: Int,
        @Query("query") query: String
    ): RecipeSearchResponse?

    @GET("recipe/get")
    suspend fun getRecipe(
        @Header("Authorization") token: String,
        @Query("id") id: Int
    ): RecipeDto?

}
