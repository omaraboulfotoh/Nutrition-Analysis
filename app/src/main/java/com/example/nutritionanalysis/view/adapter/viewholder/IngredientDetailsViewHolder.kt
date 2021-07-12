package com.example.nutritionanalysis.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionanalysis.databinding.ItemSummeryBinding
import com.example.nutritionanalysis.model.IngredientDetails

class IngredientDetailsViewHolder (
    private val binding: ItemSummeryBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: IngredientDetails) {
        binding.item = item
        binding.executePendingBindings()
    }
}