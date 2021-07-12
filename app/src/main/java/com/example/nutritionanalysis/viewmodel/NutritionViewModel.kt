package com.example.nutritionanalysis.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.nutritionanalysis.di.IODispatcher
import com.example.nutritionanalysis.model.IngredientDetails
import com.example.nutritionanalysis.model.IngredientRow
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

    fun getDailyBasis(response: NutritionDetailsResponse) =
        liveData {
            val summery: MutableList<IngredientRow> = arrayListOf()
            response.totalDaily["FAT"]?.let { summery.add(it) }
            response.totalDaily["FASAT"]?.let { summery.add(it) }
            response.totalDaily["CHOLE"]?.let { summery.add(it) }
            response.totalDaily["NA"]?.let { summery.add(it) }
            response.totalDaily["CHOCDF"]?.let { summery.add(it) }
            response.totalDaily["FIBTG"]?.let { summery.add(it) }
            response.totalDaily["PROCNT"]?.let { summery.add(it) }
            response.totalDaily["VITD"]?.let { summery.add(it) }
            response.totalDaily["CA"]?.let { summery.add(it) }
            response.totalDaily["FE"]?.let { summery.add(it) }
            response.totalDaily["K"]?.let { summery.add(it) }
            emit(summery)
        }
}