package com.example.nutritionanalysis.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nutritionanalysis.R
import com.example.nutritionanalysis.databinding.ItemSummeryBinding
import com.example.nutritionanalysis.model.IngredientDetails
import com.example.nutritionanalysis.view.adapter.viewholder.IngredientDetailsViewHolder

class SummeryAdapter :
    ListAdapter<IngredientDetails, RecyclerView.ViewHolder>(IngredientDetailsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientDetailsViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemSummeryBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_summery, parent, false
        )
        return IngredientDetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as IngredientDetailsViewHolder).bind(item)
    }

    class IngredientDetailsDiffCallback : DiffUtil.ItemCallback<IngredientDetails>() {
        override fun areItemsTheSame(old: IngredientDetails, newItem: IngredientDetails) =
            old.food == newItem.food

        override fun areContentsTheSame(old: IngredientDetails, newItem: IngredientDetails) =
            old.quantity == newItem.quantity
    }
}