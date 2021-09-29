package com.questdev.domain.interactor.usecase

import com.questdev.domain.exception.Failure
import com.questdev.domain.functional.Either
import com.questdev.domain.interactor.parent.OneShotUseCase
import com.questdev.domain.model.EmptyResult
import com.questdev.domain.repository.DataRepository

class SaveAppThemeState(
    private val dataRepository: DataRepository
) : OneShotUseCase<EmptyResult, Boolean>() {

    override suspend fun run(params: Boolean): Either<Failure, EmptyResult> {
        return dataRepository.setThemeIsDark(params)
    }

}
