package com.questdev.domain.interactor.usecase

import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.interactor.parent.UseCase
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.RecipeRepository

class SearchRecipe(
    private val repository: RecipeRepository
) : UseCase<List<Recipe>, RecipeQueryParam>() {

    override suspend fun run(params: RecipeQueryParam): Either<Failure, List<Recipe>> {
        return repository.searchRecipe(params)
    }

}
