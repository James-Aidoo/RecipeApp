package com.questdev.domain.storage

import com.questdev.domain.model.EmptyResult
import kotlinx.coroutines.flow.Flow

interface DataStorePreference {

    suspend fun setThemeIsDark(isDark: Boolean): EmptyResult

    fun getThemeIsDark(): Flow<Boolean>

}
