package com.questdev.domain.interactor.usecase

import com.questdev.domain.interactor.parent.StreamUseCase
import com.questdev.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class GetThemeIsDark(private val repository: DataRepository) : StreamUseCase<Boolean, Unit>() {

    override fun run(param: Unit): Flow<Boolean> {
        return repository.getThemeIsDark()
    }

}
