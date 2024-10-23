package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class PeriodScore(
    @SerializedName("away_score")
    val away_score: Int,
    @SerializedName("home_score")
    val home_score: Int,
    @SerializedName("number")
    val number: Int,
    @SerializedName("type")
    val type: String
)