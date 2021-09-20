package com.questdev.domain.repository

import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam

interface RecipeRepository {

    suspend fun searchRecipe(filterParam: RecipeQueryParam): Either<Failure, List<Recipe>>

    suspend fun getRecipe(id: Int): Either<Failure, Recipe>

}
