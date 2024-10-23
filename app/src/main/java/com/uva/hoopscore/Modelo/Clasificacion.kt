package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Clasificacion(
    @SerializedName("generated_at")
    val generated_at: String,
    @SerializedName("schema")
    val schema: String,
    @SerializedName("season")
    val season: Season,
    @SerializedName("standings")
    val standings: List<Standing>,
    @SerializedName("tournament")
    val tournament: Tournament
)