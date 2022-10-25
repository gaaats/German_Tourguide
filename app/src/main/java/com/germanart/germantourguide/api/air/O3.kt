package com.germanart.germantourguide.api.air


import com.google.gson.annotations.SerializedName

data class O3(
    @SerializedName("aqi")
    val aqi: Int?,
    @SerializedName("concentration")
    val concentration: Double?
)