package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class TournamentX(
    @SerializedName("category")
    val category: Category,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sport")
    val sport: Sport
)