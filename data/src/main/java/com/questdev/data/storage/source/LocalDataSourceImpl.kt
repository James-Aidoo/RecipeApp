package com.questdev.data.storage.source

import com.questdev.domain.model.EmptyResult
import com.questdev.domain.storage.DataStorePreference
import com.questdev.domain.storage.source.LocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dataStorePreference: DataStorePreference
) : LocalDataSource {

    override fun getThemeIsDark(): Flow<Boolean> {
        return dataStorePreference.getThemeIsDark()
    }

    override suspend fun setThemeIsDark(isDark: Boolean): EmptyResult {
        return dataStorePreference.setThemeIsDark(isDark)
    }

}
