package com.example.nutritionanalysis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nutritionanalysis.di.IODispatcher
import com.example.nutritionanalysis.model.IngredientDetails
import com.example.nutritionanalysis.network.data
import com.example.nutritionanalysis.network.request.IngrRequest
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import com.example.nutritionanalysis.network.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


@HiltViewModel
class NutritionViewModel @Inject constructor(
    private val getCurrenciesUseCase: UseCases.GetNutritionDetailsUseCase,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun loadDetails(request: IngrRequest) =
        liveData {
            val result =
                getCurrenciesUseCase(UseCases.GetNutritionDetailsUseCase.Params(request)).await()
            emit(result.data)
        }

    fun getSummery(response: NutritionDetailsResponse) =
        liveData {
            val summery: MutableList<IngredientDetails> = arrayListOf()
            response.ingredients.forEach {
                summery.add(it.details[0])
            }
            emit(summery)
        }
}