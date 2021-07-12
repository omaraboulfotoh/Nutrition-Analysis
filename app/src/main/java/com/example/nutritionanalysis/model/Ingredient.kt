package com.example.nutritionanalysis.model

import com.google.gson.annotations.SerializedName

data class Ingredient(
    val text: String,
    @SerializedName("parsed") val details: MutableList<IngredientDetails>
)

data class IngredientDetails(
    val food: String,
    val measure: String,
    val quantity: Double,
    val weight: Double,
    val status: String,
    val nutrients: Map<String,IngredientRow>,
)
