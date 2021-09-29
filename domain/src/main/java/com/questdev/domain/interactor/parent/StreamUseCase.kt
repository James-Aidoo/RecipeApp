package com.questdev.domain.interactor.parent

import kotlinx.coroutines.flow.Flow

abstract class StreamUseCase<out Type, in Param> where Type : Any {

    abstract fun run(param: Param): Flow<Type>

    operator fun invoke(param: Param, onResult: (Flow<Type>) -> Unit) {
        val result = run(param)
        onResult(result)
    }

}
