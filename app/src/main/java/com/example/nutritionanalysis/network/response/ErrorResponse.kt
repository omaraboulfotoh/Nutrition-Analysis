package com.example.nutritionanalysis.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ErrorResponse(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("message", alternate = ["error"])
    val message: String = "",
    @SerializedName("statusCode")
    val statusCode: Int = 0
)
