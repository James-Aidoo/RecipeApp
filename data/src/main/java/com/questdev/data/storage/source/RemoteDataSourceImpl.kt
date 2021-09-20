package com.questdev.data.storage.source

import com.questdev.data.remote.RemoteDataService
import com.questdev.domain.model.RecipeDto
import com.questdev.domain.model.RecipeQueryParam
import com.questdev.domain.model.RecipeSearchResponse
import com.questdev.domain.storage.source.RemoteDataSource
import javax.inject.Inject
import javax.inject.Named

class RemoteDataSourceImpl @Inject constructor(
    private val remoteDataService: RemoteDataService,
    @Named("token") private val token: String
) : RemoteDataSource {

    override suspend fun searchRecipe(filterParam: RecipeQueryParam): RecipeSearchResponse? {
        return remoteDataService.searchRecipe(token, filterParam.page, filterParam.query)
    }

    override suspend fun getRecipe(id: Int): RecipeDto? {
        return remoteDataService.getRecipe(token, id)
    }

}
