package com.example.nutritionanalysis.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.ItemDailyBasisBinding
import com.example.nutritionanalysis.model.IngredientRow
import com.example.nutritionanalysis.view.adapter.viewholder.IngredientRowViewHolder

class DailyBasisAdapter :
    ListAdapter<IngredientRow, RecyclerView.ViewHolder>(IngredientRowDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientRowViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemDailyBasisBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_daily_basis, parent, false
        )
        return IngredientRowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as IngredientRowViewHolder).bind(item)
    }

    class IngredientRowDiffCallback : DiffUtil.ItemCallback<IngredientRow>() {
        override fun areItemsTheSame(old: IngredientRow, newItem: IngredientRow) =
            old.label == newItem.label

        override fun areContentsTheSame(old: IngredientRow, newItem: IngredientRow) =
            old.getValue() == newItem.getValue()
    }
}