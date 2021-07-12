package com.example.nutritionanalysis.network.usecase

import com.example.nutritionanalysis.network.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

/**
 * This class represents any Use case needed to achieve or do some work
 * that might take time.
 * It identifies the input and output of the use use case.
 *
 * @param P params: wrapper represents the required parameters to do or complete the work.
 * @param R represents the output of this UseCase
 */
@Suppress("DeferredIsResult")
abstract class UseCase<P, R> {

    protected abstract val scope: CoroutineScope

    operator fun invoke(params: P): Deferred<Result<R>> =
        scope.async { doWork(params) }

    abstract suspend fun doWork(params: P): Result<R>
}
