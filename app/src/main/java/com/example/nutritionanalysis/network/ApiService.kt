package com.example.nutritionanalysis.network

import com.example.nutritionanalysis.network.request.IngrRequest
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("nutrition-details")
    suspend fun getNutritionDetails(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Body request: IngrRequest
    ): NutritionDetailsResponse
}