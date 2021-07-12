package com.example.nutritionanalysis.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionanalysis.databinding.ItemDailyBasisBinding
import com.example.nutritionanalysis.model.IngredientRow

class IngredientRowViewHolder(
    private val binding: ItemDailyBasisBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: IngredientRow) {
        binding.item = item
        binding.executePendingBindings()
    }
}