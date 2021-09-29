package com.questdev.data.repository

import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.DataRepository
import com.questdev.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dataRepository: DataRepository
) : RecipeRepository {

    override suspend fun searchRecipe(filterParam: RecipeQueryParam): Either<Failure, List<Recipe>> {
        return dataRepository.searchRecipe(filterParam)
    }

    override suspend fun getRecipe(id: Int): Either<Failure, Recipe> {
        return dataRepository.getRecipe(id)
    }

}
