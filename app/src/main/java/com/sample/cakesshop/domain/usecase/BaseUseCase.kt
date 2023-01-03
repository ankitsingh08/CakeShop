package com.sample.cakesshop.domain.usecase

import com.sample.cakesshop.domain.model.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

/**
 * Created by AnkitSingh on 12/26/22.
 */
/**
 * Executes business logic in its execute method and keep posting updates to the result as
 * [Result<R>].
 * Handling an exception (emit [ApiResponse.Error] to the result) is the subclasses's responsibility.
 */
abstract class BaseUseCase<in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {
    operator fun invoke(vararg parameters: P): Flow<ApiResponse<R>> = execute(*parameters)
        .catch { e -> emit(ApiResponse.Error(Exception(e))) }
        .flowOn(coroutineDispatcher)

    protected abstract fun execute(vararg parameters: P): Flow<ApiResponse<R>>
}