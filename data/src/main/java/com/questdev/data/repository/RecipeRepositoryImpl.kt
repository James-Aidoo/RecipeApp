package com.questdev.data.repository

import com.questdev.data.util.converter.toRecipe
import com.questdev.data.util.converter.toRecipeList
import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.RecipeRepository
import com.questdev.domain.storage.source.RemoteDataSource
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RecipeRepository {

    override suspend fun searchRecipe(filterParam: RecipeQueryParam): Either<Failure, List<Recipe>> {
        return request(
            remoteDataSource::searchRecipe,
            filterParam,
            { it?.recipes?.toRecipeList().orEmpty() }
        )
    }

    override suspend fun getRecipe(id: Int): Either<Failure, Recipe> {
        return request(remoteDataSource::getRecipe, id, { it?.toRecipe() ?: Recipe() })
    }

    private suspend fun <P, T, R> request(
        call: suspend (P) -> T,
        param: P,
        transform: (T) -> R
    ): Either<Failure, R> {
        return try {
            Either.Right(transform(call(param)))
        } catch (e: Throwable) {
            Either.Left(Failure.ServerError(e))
        }
    }

}
