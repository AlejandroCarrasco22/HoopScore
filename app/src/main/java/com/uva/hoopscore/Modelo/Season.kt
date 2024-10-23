package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("end_date")
    val end_date: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("tournament_id")
    val tournament_id: String,
    @SerializedName("year")
    val year: String
)