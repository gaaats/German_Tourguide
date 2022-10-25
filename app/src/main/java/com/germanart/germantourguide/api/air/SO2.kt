package com.germanart.germantourguide.api.air


import com.google.gson.annotations.SerializedName

data class SO2(
    @SerializedName("aqi")
    val aqi: Int?,
    @SerializedName("concentration")
    val concentration: Double?
)