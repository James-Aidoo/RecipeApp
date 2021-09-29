package com.questdev.domain.storage.source

import com.questdev.domain.model.EmptyResult
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getThemeIsDark(): Flow<Boolean>

    suspend fun setThemeIsDark(isDark: Boolean): EmptyResult

}
