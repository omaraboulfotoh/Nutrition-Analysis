package com.example.nutritionanalysis.network

import com.example.nutritionanalysis.network.response.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.util.logging.Level
import java.util.logging.Logger

interface SafeNetworkRequestCaller {
    val dispatcher: CoroutineDispatcher
    val gson: Gson
}

suspend fun <T> SafeNetworkRequestCaller.request(
    apiCall: suspend () -> T
): Result<T> = withContext(dispatcher) {
    try {
        Result.Success(apiCall.invoke())
    } catch (throwable: Exception) {
        Logger.getLogger("SafeNetworkRequestCaller.request()")
            .log(Level.SEVERE, throwable.message, throwable)

        when (throwable) {
            is IOException -> Result.Error(throwable, null)
            is HttpException -> {
                val error = gson.fromJson(
                    throwable.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                Result.Error(throwable, error, throwable.code())
            }
            else -> {
                Result.Error(throwable, null)
            }
        }
    }
}
