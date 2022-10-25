package com.germanart.germantourguide.api.recipes
import androidx.recyclerview.widget.DiffUtil

class RecipesDiffUtill: DiffUtil.ItemCallback<RecipesListNetItem>() {
    override fun areItemsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: RecipesListNetItem, newItem: RecipesListNetItem): Boolean {
        return oldItem == newItem
    }
}