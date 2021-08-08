package com.questdev.data.repository

import com.questdev.data.storage.remote.RemoteDataService
import com.questdev.data.util.converter.toRecipe
import com.questdev.data.util.converter.toRecipeList
import com.questdev.domain.model.Recipe
import com.questdev.domain.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Named

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataService: RemoteDataService,
    @Named("token") private val token: String
) : RecipeRepository {

    override suspend fun searchRecipe(page: Int, query: String): List<Recipe>? {
        return remoteDataService.searchRecipe(token, page, query)?.recipes?.toRecipeList()
    }

    override suspend fun getRecipe(id: Int): Recipe? {
        return remoteDataService.getRecipe(token, id)?.toRecipe()
    }

}