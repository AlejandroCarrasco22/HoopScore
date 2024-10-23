package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Stage(
    @SerializedName("end_date")
    val end_date: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("phase")
    val phase: String,
    @SerializedName("start_date")
    val start_date: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("year")
    val year: String
)