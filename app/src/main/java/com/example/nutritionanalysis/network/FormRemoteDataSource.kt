package com.example.nutritionanalysis.network

import com.example.nutritionanalysis.app.API_KEY
import com.example.nutritionanalysis.app.APP_ID
import com.example.nutritionanalysis.network.request.IngrRequest
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.*

/* TODO: Use Interface */
class FormRemoteDataSource(
    private val service: ApiService,
    override val gson: Gson,
    override val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : SafeNetworkRequestCaller {


    suspend fun getNutritionDetails(request: IngrRequest): Result<NutritionDetailsResponse> {
        return request { service.getNutritionDetails(APP_ID, API_KEY, request) }
    }


    companion object {
        private fun currentISODate(): String {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.US).apply {
                timeZone = TimeZone.getTimeZone("UTC")
            }.format(Date())
        }
    }
}
