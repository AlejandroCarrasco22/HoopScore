package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class CompetitorXX(
    @SerializedName("abbreviation")
    val abbreviation: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("players")
    val players: List<Player>,
    @SerializedName("qualifier")
    val qualifier: String,
    @SerializedName("statistics")
    val statistics: StatisticsXX
)