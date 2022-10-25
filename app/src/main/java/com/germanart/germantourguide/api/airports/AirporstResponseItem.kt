package com.germanart.germantourguide.api.airports


import com.google.gson.annotations.SerializedName

data class AirporstResponseItem(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country")
    val country: String?,
    @SerializedName("elevation_ft")
    val elevationFt: String?,
    @SerializedName("iata")
    val iata: String?,
    @SerializedName("icao")
    val icao: String?,
    @SerializedName("latitude")
    val latitude: String?,
    @SerializedName("longitude")
    val longitude: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("region")
    val region: String?,
    @SerializedName("timezone")
    val timezone: String?
)