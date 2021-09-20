package com.questdev.domain.storage.source

import com.questdev.domain.model.RecipeDto
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.model.RecipeSearchResponse

interface RemoteDataSource {

    suspend fun searchRecipe(filterParam: RecipeQueryParam): RecipeSearchResponse?

    suspend fun getRecipe(id: Int): RecipeDto?

}
