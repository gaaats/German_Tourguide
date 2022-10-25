package com.germanart.germantourguide.api.air


import com.google.gson.annotations.SerializedName

data class AirQualitySingle(
    @SerializedName("CO")
    val cO: CO?,
    @SerializedName("NO2")
    val nO2: NO2?,
    @SerializedName("O3")
    val o3: O3?,
    @SerializedName("overall_aqi")
    val overallAqi: Int?,
    @SerializedName("PM10")
    val pM10: PM10?,
    @SerializedName("PM2.5")
    val pM25: PM10?,
    @SerializedName("SO2")
    val sO2: SO2?
)