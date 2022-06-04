package com.example.capstonegas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.databinding.ItemIngredientBinding
import com.example.capstonegas.model.Datalistset

class IngredientAdapter(private val ingredient: Datalistset) : RecyclerView.Adapter<IngredientAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        with(holder.binding) {
            ingredientName.text = ingredient.ingredName
            detailFunction.text = ingredient.ingredFunction
            detailEffect.text = ingredient.ingredEffect

        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


    class RecyclerViewHolder(var binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)
}