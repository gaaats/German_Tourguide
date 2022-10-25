package com.germanart.germantourguide.api.air

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.germanart.germantourguide.R
import com.germanart.germantourguide.databinding.OneAirQualityBinding
import com.germanart.germantourguide.databinding.OneRecipeBinding
import com.google.android.material.snackbar.Snackbar


class AirQualityAdapterList() :
    ListAdapter<AirQualitySingle, AirQualityAdapterList.AirQualityVievHolder>(AirQualityDiffUtill()) {

    class AirQualityVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OneAirQualityBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirQualityVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.one_air_quality, parent, false).also {
                return AirQualityVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AirQualityVievHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvCo.text = currentItem.cO!!.concentration.toString()
            tvNo2.text = currentItem.nO2!!.concentration.toString()
            tvO3.text = currentItem.o3!!.concentration.toString()
            imgCurrentAirLevelLogo.setImageResource(generateImgForPager().random())
            root.setOnClickListener {
                Snackbar.make(
                    this.root,
                    "Level CO2 is ${currentItem.cO.concentration}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
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