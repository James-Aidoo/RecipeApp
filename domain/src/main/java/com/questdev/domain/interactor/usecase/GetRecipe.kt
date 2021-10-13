package com.questdev.domain.interactor.usecase

import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.interactor.parent.OneShotUseCase
import com.questdev.domain.model.Recipe
import com.questdev.domain.repository.RecipeRepository

class GetRecipe(private val repository: RecipeRepository) : OneShotUseCase<Recipe, Int>() {

    override suspend fun run(params: Int): Either<Failure, Recipe> {
        return repository.getRecipe(params)
    }

}