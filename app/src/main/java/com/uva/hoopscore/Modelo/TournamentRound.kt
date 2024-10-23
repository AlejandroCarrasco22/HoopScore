package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class TournamentRound(
    @SerializedName("group")
    val group: String,
    @SerializedName("number")
    val number: Int,
    @SerializedName("phase")
    val phase: String,
    @SerializedName("type")
    val type: String
)