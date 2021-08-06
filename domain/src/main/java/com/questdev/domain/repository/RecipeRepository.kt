package com.questdev.domain.repository

import com.questdev.domain.model.Recipe

interface RecipeRepository {

    suspend fun searchRecipe(page: Int, query: String): List<Recipe>?

    suspend fun getRecipe(id: Int): Recipe?

}