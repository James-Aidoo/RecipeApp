package com.questdev.data.repository

import com.questdev.data.util.converter.toRecipe
import com.questdev.data.util.converter.toRecipeList
import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.model.EmptyResult
import com.questdev.domain.model.Recipe
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.repository.DataRepository
import com.questdev.domain.storage.source.LocalDataSource
import com.questdev.domain.storage.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataRepository {

    override suspend fun searchRecipe(filterParam: RecipeQueryParam): Either<Failure, List<Recipe>> {
        return request(
            remoteDataSource::searchRecipe,
            filterParam,
            { it?.recipes?.toRecipeList().orEmpty() }
        )
    }

    override suspend fun getRecipe(id: Int): Either<Failure, Recipe> {
        return request(remoteDataSource::getRecipe, id, { it?.toRecipe() ?: Recipe.empty() })
    }

    override fun getThemeIsDark(): Flow<Boolean> {
        return localDataSource.getThemeIsDark()
    }

    override suspend fun setThemeIsDark(isDark: Boolean): Either<Failure, EmptyResult> {
        return request(localDataSource::setThemeIsDark, isDark)
    }

    private suspend fun <P, T, R> request(
        call: suspend (P) -> T,
        param: P,
        transform: (T) -> R
    ): Either<Failure, R> where T : Any? {
        return try {
            Either.Right(transform(call(param)))
        } catch (e: Throwable) {
            e.printStackTrace()
            Either.Left(Failure.ServerError(e))
        }
    }

    private suspend fun <P, T> request(
        call: suspend (P) -> T,
        param: P
    ): Either<Failure, T> where T : Any?, P : Any {
        return try {
            Either.Right(call(param))
        } catch (e: Throwable) {
            e.printStackTrace()
            Either.Left(Failure.ServerError(e))
        }
    }

/*    private suspend fun <T> request(
        call: suspend () -> T,
    ): Either<Failure, T> where T : Any {
        return try {
            Either.Right(call())
        } catch (e: Throwable) {
            e.printStackTrace()
            Either.Left(Failure.ServerError(e))
        }
    }

    private suspend fun <T, R> request(
        call: suspend () -> T,
        transform: (T) -> R,
    ): Either<Failure, R> {
        return try {
            Either.Right(transform(call()))
        } catch (e: Throwable) {
            e.printStackTrace()
            Either.Left(Failure.ServerError(e))
        }
    }*/

}
