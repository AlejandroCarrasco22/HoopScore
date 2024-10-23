package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Tournament(
    @SerializedName("category")
    val category: Category,
    @SerializedName("current_season")
    val current_season: CurrentSeason,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sport")
    val sport: Sport
)