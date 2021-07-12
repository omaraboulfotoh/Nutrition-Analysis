package com.example.nutritionanalysis.model

import com.example.nutritionanalysis.extention.round
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ingredient(
    val text: String,
    @SerializedName("parsed") val details: MutableList<IngredientDetails>
) : Serializable

data class IngredientDetails(
    val food: String,
    val measure: String,
    val quantity: String,
    val weight: Double,
    val status: String,
    val nutrients: Map<String, IngredientRow>,
) : Serializable {
    fun getCalories(): String {
        return nutrients["ENERC_KCAL"]?.quantity?.round().toString()
    }

    fun getWeightRounded():String{
        return weight.round().toString()
    }
}
