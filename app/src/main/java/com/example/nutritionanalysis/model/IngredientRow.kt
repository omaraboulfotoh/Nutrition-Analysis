package com.example.nutritionanalysis.model

import com.example.nutritionanalysis.extention.round
import java.io.Serializable

data class IngredientRow(val label: String, val quantity: Double, val unit: String) : Serializable {

    fun getValue():String{
        return "${quantity.round()} $unit"
    }
}