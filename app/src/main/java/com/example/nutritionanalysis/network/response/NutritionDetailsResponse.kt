package com.example.nutritionanalysis.network.response

import com.example.nutritionanalysis.extention.round
import com.example.nutritionanalysis.model.Ingredient
import com.example.nutritionanalysis.model.IngredientRow
import java.io.Serializable

data class NutritionDetailsResponse(
    val calories: String,
    val totalWeight: Double,
    val totalDaily: Map<String, IngredientRow>,
    val ingredients: MutableList<Ingredient>
) : Serializable {

    fun getTotalWeightRounded(): String {
        return totalWeight.round().toString()
    }

}