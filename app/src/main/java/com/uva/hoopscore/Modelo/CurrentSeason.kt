package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class CurrentSeason(
    @SerializedName("end_date")
    val end_date: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("year")
    val year: String
)