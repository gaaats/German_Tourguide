package com.germanart.germantourguide.api.recipes

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.germanart.germantourguide.R
import com.germanart.germantourguide.databinding.OneRecipeBinding
import com.google.android.material.snackbar.Snackbar


class CuisineAdapterList() :
    ListAdapter<RecipesListNetItem, CuisineAdapterList.HolidaysVievHolder>(RecipesDiffUtill()) {

    private var onItemClickListener: ((holiday: RecipesListNetItem) -> Unit)? = null
    private var addToFavorite: ((recipe: RecipesListNetItem) -> Unit)? = null

    class HolidaysVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OneRecipeBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.one_recipe, parent, false).also {
                return HolidaysVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolidaysVievHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvName.text = currentItem._title
            tvIngridients.text = currentItem._ingredients
            imgLogoFood.setImageResource(generateImgForPager().random())
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
            imgLogoFood.setOnClickListener {
                addToFavorite?.invoke(currentItem)
                Log.d("favorit", "pressed ${currentItem._title}")
                Snackbar.make(
                    this.root,
                    "It is ${currentItem._title}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun setOnItemClickListener(listener: (holidayName: RecipesListNetItem) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemClickListenerHeart(listener: (holidayName: RecipesListNetItem) -> Unit) {
        addToFavorite = listener
    }

    private fun generateImgForPager(): List<Int> {
        return listOf(
            R.drawable.currywurst,
            R.drawable.currywurst_,
            R.drawable.ham_leg,
            R.drawable.hot_dog,
            R.drawable.pretzel,
            R.drawable.sausages,
            R.drawable.sausages_,
            R.drawable.veal,

            )
    }
}