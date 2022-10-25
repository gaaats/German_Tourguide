package com.germanart.germantourguide.api.air


import com.google.gson.annotations.SerializedName


data class CO(
    @SerializedName("aqi")
    val aqi: Int?,
    @SerializedName("concentration")
    val concentration: Double?
)