package com.germanart.germantourguide.api.airports

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.germanart.germantourguide.R
import com.germanart.germantourguide.databinding.OneAirportBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random


class AirportsAdapterList() :
    ListAdapter<AirporstResponseItem, AirportsAdapterList.AirportViewHolder>(AirportsDiffUtill()) {

    class AirportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OneAirportBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirportViewHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.one_airport, parent, false).also {
                return AirportViewHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AirportViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.binding.apply {
            tvIata.text = currentItem.icao
            tvNameAirport.text = currentItem.name
            imgLogoAirport.setImageResource(generateImgForPager().random())
            root.setOnClickListener {
                Snackbar.make(
                    this.root,
                    "${currentItem.name} has rating ${Random.nextInt(from = 2, until = 10)}",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }


    private fun generateImgForPager(): List<Int> {
        return listOf(
            R.drawable.airport,
            R.drawable.airport2,
            R.drawable.airport3,
            R.drawable.airport4,
            R.drawable.airport4,
            R.drawable.airport_,
            R.drawable.aircraft
        )
    }
}