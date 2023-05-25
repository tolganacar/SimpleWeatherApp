package com.tolganacar.simpleweatherapp.base

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<in Params, out T : Any> {
    abstract suspend fun execute(params: Params): Flow<T>
}