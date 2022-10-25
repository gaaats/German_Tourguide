package com.germanart.germantourguide.api.airports
import androidx.recyclerview.widget.DiffUtil

class AirportsDiffUtill: DiffUtil.ItemCallback<AirporstResponseItem>() {
    override fun areItemsTheSame(oldItem: AirporstResponseItem, newItem: AirporstResponseItem): Boolean {
        return oldItem.latitude == newItem.latitude
    }

    override fun areContentsTheSame(oldItem: AirporstResponseItem, newItem: AirporstResponseItem): Boolean {
        return oldItem == newItem
    }
}