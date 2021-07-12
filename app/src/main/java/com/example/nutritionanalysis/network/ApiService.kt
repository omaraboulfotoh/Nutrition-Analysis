package com.example.nutritionanalysis.network

import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("nutrition-details")
    @FormUrlEncoded
    suspend fun getNutritionDetails(
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Field("ingr[]") ingr: MutableList<String>
    ): NutritionDetailsResponse
}