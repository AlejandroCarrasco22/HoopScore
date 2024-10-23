package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class Partidos(
    @SerializedName("generated_at")
    val generated_at: String,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("schema")
    val schema: String
)