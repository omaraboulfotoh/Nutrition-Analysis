package com.example.nutritionanalysis.network

import com.example.nutritionanalysis.network.response.ErrorResponse

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()

    data class Error(
        val exception: Exception? = null,
        val errorResponse: ErrorResponse? = null,
        val code: Int = 0
    ) : Result<Nothing>()

//    /**
//     * Represents error with string message.
//     */
//    data class GenericError(
//        val code: Int? = null, val error: String = ""
//    ) : Result<Nothing>()
//
//    /**
//     * Represents errors that has response from the backend.
//     */
//    data class HttpError(val code: Int, val error: ErrorResponse) : Result<Nothing>()
//
//    object NetworkError : Result<Nothing>()
}

/**
 * [Success.data] if [Result] is of type [Success]
 */
fun <T> Result<T>.successOr(fallback: T): T {
    return (this as? Result.Success<T>)?.data ?: fallback
}

// fun <T> Result<T>.successOrThrow(): T {
//    return (this as? Success<T>)?.data ?: throws()
// }

val <T> Result<T>.data: T?
    get() = (this as? Result.Success)?.data

val <T> Result<T>.error: Result.Error?
    get() = (this as? Result.Error)

val <T> Result<T>.isSuccessful: Boolean
    get() = this is Result.Success && this !is Error

fun <T> Result<T>.exception(): Exception {
    val errorResponse = error?.errorResponse
    if (errorResponse != null) {
        return Exception(errorResponse.message)
    }
    return error?.exception!!
}
