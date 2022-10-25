package com.germanart.germantourguide.api.air
import androidx.recyclerview.widget.DiffUtil

class AirQualityDiffUtill: DiffUtil.ItemCallback<AirQualitySingle>() {
    override fun areItemsTheSame(oldItem: AirQualitySingle, newItem: AirQualitySingle): Boolean {
        return oldItem.cO == newItem.cO
    }

    override fun areContentsTheSame(oldItem: AirQualitySingle, newItem: AirQualitySingle): Boolean {
        return oldItem == newItem
    }
}