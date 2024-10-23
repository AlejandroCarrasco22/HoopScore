package com.uva.hoopscore.Modelo

import com.google.gson.annotations.SerializedName

data class TeamStanding(
    @SerializedName("change")
    val change: Int,
    @SerializedName("current_outcome")
    val current_outcome: String,
    @SerializedName("losses")
    val losses: Int,
    @SerializedName("played")
    val played: Int,
    @SerializedName("points_against")
    val points_against: Int,
    @SerializedName("points_for")
    val points_for: Int,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("team")
    val team: Team,
    @SerializedName("wins")
    val wins: Int
)