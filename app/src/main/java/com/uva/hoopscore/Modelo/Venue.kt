package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("capacity")
    val capacity: Int,
    @SerializedName("city_name")
    val city_name: String,
    @SerializedName("country_code")
    val country_code: String,
    @SerializedName("country_name")
    val country_name: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("map_coordinates")
    val map_coordinates: String,
    @SerializedName("name")
    val name: String
)